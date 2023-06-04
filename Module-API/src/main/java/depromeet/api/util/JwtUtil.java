package depromeet.api.util;


import depromeet.api.domain.auth.dto.TokenInfo;
import depromeet.common.annotation.Util;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@Util
public class JwtUtil {

    private final RedisUtil redisUtil;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiry.refresh-token}")
    private int refreshTokenExpiryDate;

    @Value("${jwt.expiry.access-token}")
    private int accessTokenExpiryDate;

    public int getRefreshTokenExpiryDate() {
        return refreshTokenExpiryDate;
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractAllClaims(token).get("socialId", String.class);
    }

    public Date getExpiredTime(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public TokenInfo generateTokenInfo(String socialId, Platform platform, Role role) {
        String accessToken = generateAccessToken(socialId, role, platform);
        String refreshToken = generateRefreshToken(socialId, role, platform);
        return new TokenInfo(accessToken, refreshToken);
    }

    public String generateAccessToken(String socialId, Role role, Platform platform) {
        return doGenerateToken(socialId, platform, role, accessTokenExpiryDate);
    }

    public String generateRefreshToken(String socialId, Role role, Platform platform) {
        return doGenerateToken(socialId, platform, role, refreshTokenExpiryDate);
    }

    public String doGenerateToken(String socialId, Platform platform, Role role, int expireTime) {
        Claims claims = Jwts.claims();
        claims.put("socialId", socialId);
        claims.put("platform", platform);
        claims.put("role", role.getName());

        return Jwts.builder()
                .setSubject(socialId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, getSigningKey(SECRET_KEY))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("AUTHORIZATION");
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims =
                    Jwts.parser().setSigningKey(getSigningKey(SECRET_KEY)).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private void storeRefreshToken(String key, String value) {
        redisUtil.setDataExpire(key, value, refreshTokenExpiryDate);
    }

    public TokenInfo issueToken(String id, Platform platform, Role role) {
        TokenInfo tokenInfo = generateTokenInfo(id, platform, role);
        storeRefreshToken(id, tokenInfo.getRefreshToken());
        return tokenInfo;
    }
}
