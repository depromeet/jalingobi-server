package depromeet.domain.challenge.domain;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.*;

@Builder
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Duration {

    @Column(nullable = false)
    private int period;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;
}
