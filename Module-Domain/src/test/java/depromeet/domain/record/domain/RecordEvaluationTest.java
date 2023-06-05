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

        RecordEvaluation enumTwo = RecordEvaluation.getEnumTypeByValue(2);
        Assertions.assertEquals(RecordEvaluation.TWO, enumTwo);

        RecordEvaluation enumThree = RecordEvaluation.getEnumTypeByValue(3);
        Assertions.assertEquals(RecordEvaluation.THREE, enumThree);

        RecordEvaluation enumFour = RecordEvaluation.getEnumTypeByValue(4);
        Assertions.assertEquals(RecordEvaluation.FOUR, enumFour);
    }

    @Test
    @DisplayName("[Record Evaluation] 존재하지 않는 enum 타입")
    void RecordEvaluationTest2() throws Exception {
        assertThatThrownBy(() -> RecordEvaluation.getEnumTypeByValue(6))
                .isInstanceOf(RecordEvaluationNotFoundException.class);

        assertThatThrownBy(() -> RecordEvaluation.getEnumTypeByValue(15))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
    }
}
