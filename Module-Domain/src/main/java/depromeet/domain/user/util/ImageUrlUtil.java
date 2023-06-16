package depromeet.domain.user.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlUtil {
    public static String defaultImgUrl;

    @Value("${image.default.profile}")
    public void setDefaultImgUrl(String value) {
        defaultImgUrl = value;
    }
}
