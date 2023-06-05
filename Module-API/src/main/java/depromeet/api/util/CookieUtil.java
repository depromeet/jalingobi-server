package depromeet.api.util;


import depromeet.common.annotation.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Util
public class CookieUtil {

    private final JwtUtil jwtUtil;

    public Cookie createCookie(String cookieName, String value) {
        Cookie token = new Cookie(cookieName, value);
        token.setHttpOnly(true);
        token.setSecure(true);
        token.setMaxAge(jwtUtil.getRefreshTokenExpiryDate());
        token.setPath("/");
        return token;
    }

    public Cookie setRefreshToken(String value) {
        Cookie token = new Cookie("RefreshToken", value);
        token.setHttpOnly(true);
        token.setSecure(true);
        token.setMaxAge(jwtUtil.getRefreshTokenExpiryDate());
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) return cookie;
        }
        return null;
    }
}
