package depromeet.api.util;


import depromeet.common.annotation.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

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
        Cookie cookie = new Cookie("RefreshToken", value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(jwtUtil.getRefreshTokenExpiryDate());
        cookie.setPath("/");
        return cookie;
    }

    public void setRefreshToken(HttpServletResponse response, String value) {
        ResponseCookie cookie =
                ResponseCookie.from("RefreshToken", value)
                        .path("/")
                        .sameSite("None")
                        .httpOnly(true)
                        .secure(true)
                        .maxAge(jwtUtil.getRefreshTokenExpiryDate())
                        .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
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
