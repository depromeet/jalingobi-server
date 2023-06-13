package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class StatusNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new StatusNotFoundException();

    private StatusNotFoundException() {
        super(CustomExceptionStatus.STATUS_NOT_FOUND);
    }
}
