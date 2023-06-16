package depromeet.api.domain.image.dto.response;


import depromeet.api.domain.image.dto.ImageUrlDto;
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
            example = "record/2817216430/7e3e0dca-2491-4764-80f2-593166d9712b.png")
    private final String key;

    public static IssuePresignedUrlResponse from(ImageUrlDto urlDto) {
        return IssuePresignedUrlResponse.builder()
                .presignedUrl(urlDto.getPresignedUrl())
                .key(urlDto.getKey())
                .build();
    }
}
