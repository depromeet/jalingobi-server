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
        Evaluation enumOne = Evaluation.getEnumTypeByValue(1);
        Assertions.assertEquals(Evaluation.ONE, enumOne);

        Evaluation enumTwo = Evaluation.getEnumTypeByValue(2);
        Assertions.assertEquals(Evaluation.TWO, enumTwo);

        Evaluation enumThree = Evaluation.getEnumTypeByValue(3);
        Assertions.assertEquals(Evaluation.THREE, enumThree);

        Evaluation enumFour = Evaluation.getEnumTypeByValue(4);
        Assertions.assertEquals(Evaluation.FOUR, enumFour);
    }

    @Test
    @DisplayName("[Record Evaluation] 존재하지 않는 enum 타입")
    void RecordEvaluationTest2() throws Exception {
        assertThatThrownBy(() -> Evaluation.getEnumTypeByValue(6))
                .isInstanceOf(RecordEvaluationNotFoundException.class);

        assertThatThrownBy(() -> Evaluation.getEnumTypeByValue(15))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
    }
}
