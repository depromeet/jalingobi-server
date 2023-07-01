package depromeet.domain.record.domain;


import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Evaluation {
    WELLDONE("WELLDONE"),
    REGRETFUL("REGRETFUL"),
    CRAZY("CRAZY");

    private final String value;

    public static Evaluation getEnumTypeByValue(String value) {
        return Arrays.stream(Evaluation.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> RecordEvaluationNotFoundException.EXCEPTION);
    }
}
