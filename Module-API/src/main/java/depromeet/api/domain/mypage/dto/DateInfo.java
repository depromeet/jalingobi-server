package depromeet.api.domain.mypage.dto;


import depromeet.domain.challenge.domain.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DateInfo {

    private Integer period;

    private LocalDate startAt;

    private LocalDate endAt;

    public DateInfo(Duration duration) {
        this.period = duration.getPeriod();
        this.startAt = duration.getStartAt();
        this.endAt = duration.getEndAt();
    }
}
