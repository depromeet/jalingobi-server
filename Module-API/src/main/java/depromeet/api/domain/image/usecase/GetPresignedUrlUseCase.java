package depromeet.api.domain.image.usecase;


import depromeet.api.config.s3.ImageFileExtension;
import depromeet.api.config.s3.S3UploadPresignedUrlService;
import depromeet.api.domain.image.dto.response.GetPresignedUrlResponse;
import depromeet.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPresignedUrlUseCase {
    private final S3UploadPresignedUrlService uploadPresignedUrlService;

    public GetPresignedUrlResponse execute(String socialId, ImageFileExtension imageFileExtension) {

        return GetPresignedUrlResponse.from(
                uploadPresignedUrlService.execute(socialId, imageFileExtension));
    }
}
