package depromeet.api.domain.challenge.dto.response.feed;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeFeedResponse {

    @Schema(description = "챌린지 ID", example = "1")
    private Long challengeId;

    @Schema(description = "챌린지에 참가한 인원수", example = "30")
    private Integer curCount;

    @Schema(description = "챌린지에 참가할 수 있는 최대 인원수", example = "50")
    private Integer availableCount;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 대표 이미지", example = "/test.jpg")
    private String imgUrl;

    @Schema(description = "키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    private List<String> keywords;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    private LocalDate startAt;

    @Schema(description = "챌린지 기간", example = "30")
    private Integer period;
}
