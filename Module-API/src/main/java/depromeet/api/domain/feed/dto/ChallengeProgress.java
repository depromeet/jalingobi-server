package depromeet.api.domain.feed.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChallengeProgress {

    @Schema(description = "목표 지출액", example = "100000")
    private Integer goalCharge;

    @Schema(description = "현재 지출액", example = "65000")
    private Integer currentCharge;

    @Schema(description = "퍼센트(%)", example = "65")
    private Integer percent;

    @Schema(description = "남은 기간", example = "11")
    private Integer dueDay;
}
