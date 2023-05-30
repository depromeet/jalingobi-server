package depromeet.api.domain.auth.service;


import depromeet.api.util.JwtUtil;
import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository accountRepository;

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
        User account =
                accountRepository
                        .findBySocialId(socialId)
                        .orElseThrow(
                                () -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        return jwtUtil.generateAccessToken(
                account.getSocial().getId(), account.getRole(), account.getSocial().getPlatform());
    }
}
