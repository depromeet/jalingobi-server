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
        Evaluation crazy = Evaluation.getEnumTypeByValue("CRAZY");
        Assertions.assertEquals(Evaluation.CRAZY, crazy);

        Evaluation regretful = Evaluation.getEnumTypeByValue("REGRETFUL");
        Assertions.assertEquals(Evaluation.REGRETFUL, regretful);

        Evaluation welldone = Evaluation.getEnumTypeByValue("WELLDONE");
        Assertions.assertEquals(Evaluation.WELLDONE, welldone);
    }

    @Test
    @DisplayName("[Record Evaluation] 존재하지 않는 enum 타입")
    void RecordEvaluationTest2() throws Exception {
        assertThatThrownBy(() -> Evaluation.getEnumTypeByValue("hoho"))
                .isInstanceOf(RecordEvaluationNotFoundException.class);

        assertThatThrownBy(() -> Evaluation.getEnumTypeByValue("good"))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
    }
}
