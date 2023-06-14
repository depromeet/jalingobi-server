package depromeet.api.domain.mypage.usecase;


import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateProfileUseCase {
    private final UserAdaptor userAdaptor;

    public void execute(String socialId, UpdateProfileRequest updateProfileRequest) {
        User user = userAdaptor.findUser(socialId);

        user.updateProfile(
                updateProfileRequest.getNickName(), updateProfileRequest.getProfileImgUrl());
    }
}
