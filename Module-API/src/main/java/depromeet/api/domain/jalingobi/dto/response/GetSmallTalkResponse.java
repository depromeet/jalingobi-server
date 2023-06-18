package depromeet.api.domain.jalingobi.dto.response;


import depromeet.domain.jalingobi.domain.Level;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetSmallTalkResponse {
    @Schema(description = "현재 Small Talk을 말하는 자린고비 레벨")
    private Level level;

    @Schema(example = "뭐, 대충 살고있어요")
    private String smallTalk;

    public static GetSmallTalkResponse of(Level level, String smallTalk) {
        return GetSmallTalkResponse.builder().level(level).smallTalk(smallTalk).build();
    }
}
