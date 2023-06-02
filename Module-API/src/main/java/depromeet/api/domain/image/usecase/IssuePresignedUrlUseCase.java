package depromeet.api.domain.image.usecase;


import depromeet.api.config.s3.S3UploadPresignedUrlService;
import depromeet.api.domain.image.dto.ImageFileExtension;
import depromeet.api.domain.image.dto.ImageUploadType;
import depromeet.api.domain.image.dto.response.IssuePresignedUrlResponse;
import depromeet.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class IssuePresignedUrlUseCase {
    private final S3UploadPresignedUrlService uploadPresignedUrlService;

    public IssuePresignedUrlResponse execute(
            String socialId, ImageFileExtension imageFileExtension, ImageUploadType type) {

        return IssuePresignedUrlResponse.from(
                uploadPresignedUrlService.execute(socialId, imageFileExtension, type));
    }
}
