package depromeet.api.domain.record.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidUserRecordException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidUserRecordException();

    private InvalidUserRecordException() {
        super(CustomExceptionStatus.INVALID_RECORD_USER);
    }
}
