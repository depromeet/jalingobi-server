package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ProgressInfoNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ProgressInfoNotFoundException();

    private ProgressInfoNotFoundException() {
        super(CustomExceptionStatus.PROGRESS_INFO_NOT_FOUND);
    }
}
