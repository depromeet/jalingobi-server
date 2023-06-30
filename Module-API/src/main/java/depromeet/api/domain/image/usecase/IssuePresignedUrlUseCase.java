package depromeet.api.domain.image.usecase;


import depromeet.api.config.s3.S3UploadPresignedUrlService;
import depromeet.api.domain.image.dto.request.IssuePresignedUrlRequest;
import depromeet.api.domain.image.dto.response.IssuePresignedUrlResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class IssuePresignedUrlUseCase {

    private final UserAdaptor userAdaptor;
    private final S3UploadPresignedUrlService uploadPresignedUrlService;

    public IssuePresignedUrlResponse execute(
            String socialId, IssuePresignedUrlRequest issuePresignedUrlRequest) {

        userAdaptor.doesUserExist(socialId);

        return IssuePresignedUrlResponse.from(
                uploadPresignedUrlService.execute(
                        issuePresignedUrlRequest.getImageFileExtension(),
                        issuePresignedUrlRequest.getType()));
    }
}
