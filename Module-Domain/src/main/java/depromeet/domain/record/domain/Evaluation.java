package depromeet.domain.record.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Evaluation {
    WELLDONE("WELLDONE"),
    REGRETFUL("REGRETFUL"),
    CRAZY("CRAZY");

    private final String value;
}
