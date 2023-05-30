package depromeet.domain.record.domain;


import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RecordEvaluation {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    public RecordEvaluation getEnumTypeByValue(int value) {
        return Arrays.stream(RecordEvaluation.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() -> RecordEvaluationNotFoundException.EXCEPTION);

    }
}
