package depromeet.api.util;


import depromeet.api.domain.auth.dto.TokenInfo;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final RedisUtil redisUtil;
    private final UserDetailsService userDetailsService;

    static final long ACCESS_TOKEN_VALIDATION_SECOND = 60 * 60L;
    public static final long REFRESH_TOKEN_VALIDATION_SECOND = 60 * 60 * 24 * 7L;
    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
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
        return doGenerateToken(socialId, platform, role, ACCESS_TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(String socialId, Role role, Platform platform) {
        return doGenerateToken(socialId, platform, role, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(String socialId, Platform platform, Role role, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("platform", platform);
        claims.put("role", role);

        return Jwts.builder()
                .setSubject(socialId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
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

    public boolean validateToken(String jwtToken, HttpServletRequest req) {
        try {
            if (jwtToken.isEmpty()) throw new JwtException("empty jwtToken");
            Jws<Claims> claimsJws =
                    Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            if (jwtToken.isEmpty()) {
                req.setAttribute("exception", CustomExceptionStatus.EMPTY_JWT.getMessage());
            } else req.setAttribute("exception", CustomExceptionStatus.INVALID_JWT.getMessage());
            return false;
        }
    }

    public void storeRefreshToken(String key, String value) {
        redisUtil.setDataExpire(key, value, REFRESH_TOKEN_VALIDATION_SECOND);
    }
}
