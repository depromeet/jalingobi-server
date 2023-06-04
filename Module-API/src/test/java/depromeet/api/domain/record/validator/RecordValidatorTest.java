package depromeet.api.domain.record.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecordValidatorTest {

    @Test
    @DisplayName("[예외] 평가 1,2,3,4,5가 아닌 점수가 들어왔을 때")
    public void outOfRangeTest() throws Exception {
        // given
        RecordValidator recordValidator = new RecordValidator();

        assertThatThrownBy(() -> recordValidator.validateEvaluationType(6))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
        assertThatThrownBy(() -> recordValidator.validateEvaluationType(155))
                .isInstanceOf(RecordEvaluationNotFoundException.class);
    }
}
