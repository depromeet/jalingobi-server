package depromeet.domain.record.exception;

import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class RecordNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new RecordNotFoundException();

    private RecordNotFoundException() {
        super(CustomExceptionStatus.RECORD_NOT_FOUND);
    }
}
