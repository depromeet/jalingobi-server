package depromeet.domain.record.domain;


import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Evaluation {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    private final int value;

    public static Evaluation getEnumTypeByValue(int value) {
        return Arrays.stream(Evaluation.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() -> RecordEvaluationNotFoundException.EXCEPTION);
    }
}
