package depromeet.api.domain.record.validator;


import depromeet.domain.record.domain.RecordEvaluation;
import depromeet.domain.record.exception.RecordEvaluationNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordValidator {
    public void validateEvaluationType(int evaluation) {
        try {
            RecordEvaluation.getEnumTypeByValue(evaluation);
        } catch (RecordEvaluationNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
