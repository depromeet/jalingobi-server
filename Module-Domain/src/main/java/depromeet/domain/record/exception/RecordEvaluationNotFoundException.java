package depromeet.domain.record.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class RecordEvaluationNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new RecordEvaluationNotFoundException();

    private RecordEvaluationNotFoundException() {
        super(CustomExceptionStatus.RECORD_EVALUATION_NOT_VALID);
    }
}
