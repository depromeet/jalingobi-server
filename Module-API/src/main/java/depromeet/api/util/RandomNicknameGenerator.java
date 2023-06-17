package depromeet.api.util;


import depromeet.api.domain.challenge.dto.response.CreateRandomNicknameResponse;
import depromeet.common.annotation.Util;
import depromeet.domain.challenge.domain.NicknameType;
import java.util.Random;

@Util
public class RandomNicknameGenerator {

    private static final Random RANDOM = new Random();
    private static final NicknameType[] arr = NicknameType.values();

    public static CreateRandomNicknameResponse generate() {
        NicknameType nicknameType = arr[RANDOM.nextInt(arr.length)];
        return CreateRandomNicknameResponse.of(nicknameType.getName());
    }
}
