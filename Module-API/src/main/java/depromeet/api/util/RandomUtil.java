package depromeet.api.util;


import depromeet.api.util.exception.ListNotNullException;
import depromeet.common.annotation.Util;
import java.util.List;
import java.util.Random;

@Util
public class RandomUtil {
    private static final Random random = new Random();

    public static <T> T getRandomItem(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw ListNotNullException.EXCEPTION;
        }

        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
