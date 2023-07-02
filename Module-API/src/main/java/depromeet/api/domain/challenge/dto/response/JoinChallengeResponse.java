package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JoinChallengeResponse {

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 시작 일자")
    private LocalDate startAt;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    private List<String> rules;

    public static JoinChallengeResponse of(Challenge challenge) {
        return JoinChallengeResponse.builder()
                .title(challenge.getTitle())
                .startAt(challenge.getDuration().getStartAt())
                .rules(challenge.getChallengeRuleContents())
                .build();
    }
}
