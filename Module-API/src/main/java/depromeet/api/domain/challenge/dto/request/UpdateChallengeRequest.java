package depromeet.api.domain.challenge.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "챌린지 ID를 입력하세요.")
    private Long ChallengeId;

    @Schema(description = "챌린지가 속한 카테고리", example = "[\"식비\"]")
    @NotNull(message = "카테고리를 선택하세요.")
    private List<@NotBlank String> categories;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @Schema(description = "챌린지 목표 금액", example = "100000")
    @Min(value = 10000, message = "최소 목표 금액은 10,000입니다.")
    @Max(value = 999999, message = "최대 목표 금액은 999,999입니다.")
    @NotNull(message = "목표 금액을 입력하세요.")
    private Integer price;

    @Schema(description = "챌린지 대표 이미지", example = "/test.jpg")
    private String imgUrl;

    @Schema(description = "키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    @NotNull(message = "키워드를 입력하세요.")
    private List<@NotBlank String> keywords;

    @Schema(description = "챌린지 수용 인원", example = "30")
    @Min(value = 5, message = "챌린지 수용 인원은 최소 5명입니다.")
    @Max(value = 50, message = "챌린지 수용 인원은 최대 50명입니다.")
    @NotNull(message = "챌린지 수용 인원을 입력하세요.")
    private Integer availableCount;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    @NotNull(message = "챌린지 규칙을 입력하세요.")
    private List<@NotBlank String> rules;

    @Schema(description = "챌린지 기간", example = "7")
    @NotNull(message = "챌린지 기간을 입력하세요.")
    private Integer period;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    @NotNull(message = "챌린지 시작 일자를 입력하세요.")
    private LocalDate startAt;

    @Schema(description = "챌린지 종료 일자", example = "2023-06-07")
    @NotNull(message = "챌린지 종료 일자를 입력하세요.")
    private LocalDate endAt;
}
