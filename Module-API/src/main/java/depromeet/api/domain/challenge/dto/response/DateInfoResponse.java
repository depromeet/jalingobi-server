package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Duration;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DateInfoResponse {

    @Schema(description = "챌린지 기간", example = "30")
    private int period;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    private LocalDate startAt;

    @Schema(description = "챌린지 종료 일자", example = "2023-06-30")
    private LocalDate endAt;

    public DateInfoResponse toDateInfoResponse(Duration duration) {
        return new DateInfoResponse(
                duration.getPeriod(), duration.getStartAt(), duration.getEndAt());
    }
}
