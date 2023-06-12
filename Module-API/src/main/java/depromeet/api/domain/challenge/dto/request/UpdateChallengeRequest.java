package depromeet.api.domain.challenge.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeRequest {

    @Schema(description = "챌린지 ID", example = "1")
    private Long ChallengeId;

    @Schema(description = "챌린지가 속한 카테고리", example = "[\"식비\"]")
    private List<String> categories;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 목표 금액", example = "100000")
    private Integer price;

    @Schema(description = "챌린지 대표 이미지", example = "/test.jpg")
    private String imgUrl;

    @Schema(description = "키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    private List<String> keywords;

    @Schema(description = "챌린지 수용 인원", example = "30")
    private Integer availableCount;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    private List<String> rules;

    @Schema(description = "챌린지 기간", example = "7")
    private Integer period;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    private LocalDate startAt;

    @Schema(description = "챌린지 종료 일자", example = "2023-06-07")
    private LocalDate endAt;
}
