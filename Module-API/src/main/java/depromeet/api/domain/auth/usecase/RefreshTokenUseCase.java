package depromeet.api.domain.auth.usecase;


import depromeet.api.domain.auth.dto.TokenInfo;
import depromeet.api.domain.auth.dto.response.TokenResponse;
import depromeet.api.util.JwtUtil;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final JwtUtil jwtUtil;
    private final UserAdaptor userAdaptor;

    @Transactional(readOnly = true)
    public TokenResponse execute(String refreshToken) {
        String socialId = jwtUtil.getUsername(refreshToken);

        //        if (refresh == null) {
        //            throw new CustomException(CustomExceptionStatus.INVALID_REFRESH_TOKEN);
        //        }
        //        if (!refresh.equals(refreshToken)) {
        //            throw new CustomException(CustomExceptionStatus.INVALID_REFRESH_TOKEN);
        //        }

        User account = userAdaptor.findUser(socialId);

        TokenInfo reissue =
                jwtUtil.reissue(
                        account.getSocial().getId(),
                        account.getSocial().getPlatform(),
                        account.getRole(),
                        refreshToken);

        return TokenResponse.of(reissue.getRefreshToken(), reissue.getAccessToken());
    }
}
