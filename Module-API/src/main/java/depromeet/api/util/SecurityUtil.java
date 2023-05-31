package depromeet.api.util;


import depromeet.domain.user.domain.Social;
import depromeet.domain.user.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentUserSocialId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = (User) principal;
        Social social = currentUser.getSocial();
        return social.getId();
    }
}
