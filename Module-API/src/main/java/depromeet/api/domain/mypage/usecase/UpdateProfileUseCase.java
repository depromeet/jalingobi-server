package depromeet.api.domain.mypage.usecase;


import depromeet.api.config.s3.S3UploadPresignedUrlService;
import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateProfileUseCase {
    @Value("${image.default.profile}")
    private String imgUrl;

    @Value("${image.prefix}")
    private String prefix;

    private final UserAdaptor userAdaptor;
    private final S3UploadPresignedUrlService uploadPresignedUrlService;

    public void execute(String socialId, UpdateProfileRequest updateProfileRequest) {
        User user = userAdaptor.findUser(socialId);
        String currentImgUrl = user.getProfile().getImgUrl();
        String key = getKey(currentImgUrl);

        if (!currentImgUrl.equals(imgUrl)) {
            uploadPresignedUrlService.deleteImage(key);
        }

        user.updateProfile(
                updateProfileRequest.getNickName(), updateProfileRequest.getProfileImgUrl());
    }

    private String getKey(String imgUrl) {
        String[] splitUrl = imgUrl.split(prefix);
        return splitUrl[1];
    }
}
