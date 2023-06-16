package depromeet.api.domain.mypage.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetJalingobiImgResponse {

    @Schema(example = "사용자 자린고비 이미지 URL")
    private String imgUrl;

    public static GetJalingobiImgResponse of(String imgUrl) {
        return GetJalingobiImgResponse.builder().imgUrl(imgUrl).build();
    }
}
