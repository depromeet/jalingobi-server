package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Duration;
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

    private int period;

    private LocalDate startAt;

    private LocalDate endAt;

    public DateInfoResponse toDateInfoResponse(Duration duration) {
        return new DateInfoResponse(
                duration.getPeriod(), duration.getStartAt(), duration.getEndAt());
    }
}
