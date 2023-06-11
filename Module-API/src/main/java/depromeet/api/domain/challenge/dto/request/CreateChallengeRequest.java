package depromeet.api.domain.challenge.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChallengeRequest {

    @Schema(description = "챌린지가 속한 카테고리", example = "[\"식비\"]")
    @NotNull
    private List<@NotBlank String> category;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    @NotBlank(message = "title can not be blank")
    private String title;

    @Schema(description = "챌린지 목표 금액", example = "100000")
    @Min(10000)
    @Max(999999)
    @NotNull
    private Integer price;

    @Schema(description = "챌린지 대표 이미지", example = "/test.jpg")
    private String imageUrl;

    @Schema(description = "키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    @NotNull
    private List<@NotBlank String> keywords;

    @Schema(description = "챌린지 수용 인원", example = "30")
    @Min(5)
    @Max(50)
    @NotNull
    private Integer availableCount;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    private List<String> challengeRule;

    @Schema(description = "챌린지 기간", example = "7")
    @NotNull
    private Integer period;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    @NotNull
    private LocalDate startAt;

    @Schema(description = "챌린지 종료 일자", example = "2023-06-07")
    @NotNull
    private LocalDate endAt;
}
