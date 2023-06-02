package depromeet.api.domain.image.dto.response;


import depromeet.api.domain.image.dto.ImageUrlDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssuePresignedUrlResponse {
    private final String presignedUrl;
    private final String key;

    public static IssuePresignedUrlResponse from(ImageUrlDto urlDto) {
        return IssuePresignedUrlResponse.builder()
                .presignedUrl(urlDto.getPresignedUrl())
                .key(urlDto.getKey())
                .build();
    }
}
