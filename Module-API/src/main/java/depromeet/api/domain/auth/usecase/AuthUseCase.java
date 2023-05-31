package depromeet.api.domain.auth.usecase;


import depromeet.api.util.JwtUtil;
import depromeet.common.annotation.UseCase;
import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class AuthUseCase {

    private final JwtUtil jwtUtil;
    private final UserAdaptor userAdaptor;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public String checkRefreshToken(String token, String refreshToken) {
        String socialId = jwtUtil.getUsername(token);
        String refresh = redisTemplate.opsForValue().get(socialId);

        if (refresh == null) {
            throw new CustomException(CustomExceptionStatus.INVALID_JWT);
        }
        if (!refresh.equals(refreshToken)) {
            throw new CustomException(CustomExceptionStatus.INVALID_JWT);
        }
        User account = userAdaptor.findUser(socialId);

        return jwtUtil.generateAccessToken(
                account.getSocial().getId(), account.getRole(), account.getSocial().getPlatform());
    }
}
