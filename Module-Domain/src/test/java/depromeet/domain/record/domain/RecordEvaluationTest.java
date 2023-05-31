package depromeet.domain.record.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecordEvaluationTest {
    @Test
    @DisplayName("[Record Evaluation] enum 타입으로 변환하는 테스트")
    void RecordEvaluationTest() throws Exception {
        RecordEvaluation enumOne = RecordEvaluation.getEnumTypeByValue(1);
        Assertions.assertEquals(RecordEvaluation.ONE, enumOne);

        RecordEvaluation enumThree = RecordEvaluation.getEnumTypeByValue(3);
        Assertions.assertEquals(RecordEvaluation.THREE, enumThree);

        assertThatThrownBy(() -> RecordEvaluation.getEnumTypeByValue(6))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
    }
}
