package depromeet.api.domain.mypage.usecase;


import depromeet.api.util.RedisUtil;
import depromeet.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LogoutUseCase {

    private final RedisUtil redisUtil;

    public void execute(String socialId) {
        redisUtil.deleteData(socialId);
    }
}
