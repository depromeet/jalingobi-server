package depromeet.api.domain.image.dto.response;


import depromeet.api.domain.image.dto.ImageUrlDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPresignedUrlResponse {
    private final String presignedUrl;
    private final String key;

    public static GetPresignedUrlResponse from(ImageUrlDto urlDto) {
        return GetPresignedUrlResponse.builder()
                .presignedUrl(urlDto.getPresignedUrl())
                .key(urlDto.getKey())
                .build();
    }
}
