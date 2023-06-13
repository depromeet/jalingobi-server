package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class DuplicateParticipationException extends CustomException {
    public static final CustomException EXCEPTION = new DuplicateParticipationException();

    private DuplicateParticipationException() {
        super(CustomExceptionStatus.DUPLICATE_PARTICIPATION);
    }
}
