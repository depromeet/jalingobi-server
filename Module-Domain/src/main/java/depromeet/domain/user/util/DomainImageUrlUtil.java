package depromeet.domain.user.util;


import depromeet.common.annotation.Util;
import org.springframework.beans.factory.annotation.Value;

@Util
public class DomainImageUrlUtil {
    public static String defaultImgUrl;

    @Value("${image.default.profile}")
    public void setDefaultImgUrl(String value) {
        defaultImgUrl = value;
    }
}
