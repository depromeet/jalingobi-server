package depromeet.api.util;


import depromeet.common.annotation.Util;
import org.springframework.beans.factory.annotation.Value;

@Util
public class ApiImageUrlUtil {
    public static String prefix;

    @Value("${image.prefix}")
    public void setPrefix(String value) {
        prefix = value;
    }
}
