package depromeet.api.domain.jalingobi.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetSmallTalkResponse {

    @Schema(example = "뭐, 대충 살고있어요")
    private String smallTalk;

    public static GetSmallTalkResponse of(String smallTalk) {
        return GetSmallTalkResponse.builder().smallTalk(smallTalk).build();
    }
}
