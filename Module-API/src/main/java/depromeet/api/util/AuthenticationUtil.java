package depromeet.api.util;


import depromeet.api.config.security.CustomUserDetails;
import depromeet.common.annotation.Util;
import org.springframework.security.core.context.SecurityContextHolder;

@Util
public class AuthenticationUtil {
    public static String getCurrentUserSocialId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails currentUser = (CustomUserDetails) principal;
        return currentUser.getUsername();
    }
}
