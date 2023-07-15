package depromeet.api.util;


import depromeet.common.annotation.Util;
import org.springframework.beans.factory.annotation.Value;

@Util
public class ImageUrlUtil {
    public static String prefix;

    @Value("${image.prefix}")
    public void setPrefix(String value) {
        prefix = value;
    }
}
