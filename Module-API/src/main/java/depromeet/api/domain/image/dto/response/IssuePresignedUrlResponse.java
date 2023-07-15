package depromeet.api.domain.image.dto.response;


import depromeet.api.domain.image.dto.ImageUrlDto;
import depromeet.api.util.ImageUrlUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssuePresignedUrlResponse {
    @Schema(example = "업로드할 때 요청할 url")
    private final String presignedUrl;

    @Schema(
            description = "s3에 저장될 경로",
            example = "https://jalingobi-bucket-test.s3.ap-northeast-2.amazonaws.com/record/original/7e3e0dca-2491-4764-80f2-593166d9712b.png")
    private final String imgUrl;

    public static IssuePresignedUrlResponse from(ImageUrlDto urlDto) {
        String imgUrl = ImageUrlUtil.prefix + urlDto.getKey();

        return IssuePresignedUrlResponse.builder()
                .presignedUrl(urlDto.getPresignedUrl())
                .imgUrl(imgUrl)
                .build();
    }
}
