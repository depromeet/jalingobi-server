package depromeet.api.domain.auth.validator;

import static org.springframework.security.oauth2.jwt.JoseHeaderNames.KID;

import depromeet.api.domain.auth.exception.InvalidJwtException;
import depromeet.api.domain.auth.feign.Keys;
import depromeet.api.domain.auth.feign.Keys.PubKey;
import depromeet.api.domain.auth.feign.UserInfo;
import io.jsonwebtoken.*;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthValidator {

    @Value("${oauth.aud.kakao}")
    private String kakaoAud;

    private final depromeet.api.domain.auth.feign.kakaoAuthFeignClient kakaoAuthFeignClient;
    private final depromeet.api.domain.auth.feign.kakaoApiFeignClient kakaoApiFeignClient;

    public void sigVerification(String idToken) {
        // 토큰 서명 검증
        Jws<Claims> oidcTokenJws = sigVerificationAndGetJws(idToken);
    }

    public UserInfo reqUserInfo(String accessToken) {
        String authorization = "Bearer " + accessToken;
        return kakaoApiFeignClient.getUserInfo(authorization);
    }

    private Jws<Claims> sigVerificationAndGetJws(String idToken) {
        String kid = getKidFromUnsignedTokenHeader(idToken, "https://kauth.kakao.com", kakaoAud);

        Keys keys = kakaoAuthFeignClient.getKeys();
        PubKey pubKey =
                keys.getKeys().stream().filter((key) -> key.getKid().equals(kid)).findAny().get();

        return getOIDCTokenJws(idToken, pubKey.getN(), pubKey.getE());
    }

    public Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getRSAPublicKey(modulus, exponent))
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            log.error(e.toString());
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private Key getRSAPublicKey(String modulus, String exponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }

    private String getKidFromUnsignedTokenHeader(String token, String iss, String aud) {
        return (String) getUnsignedTokenClaims(token, iss, aud).getHeader().get(KID);
    }

    private Jwt<Header, Claims> getUnsignedTokenClaims(String token, String iss, String aud) {
        try {
            return Jwts.parserBuilder()
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(getUnsignedToken(token));
        } catch (Exception e) {
            log.error(e.toString());
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private String getUnsignedToken(String token) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) throw InvalidJwtException.EXCEPTION;
        return splitToken[0] + "." + splitToken[1] + ".";
    }
}
