package depromeet.api.domain.record.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidChallengeStatusException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidChallengeStatusException();

    private InvalidChallengeStatusException() {
        super(CustomExceptionStatus.CHALLENGE_NOT_IN_PROCEEDING);
    }
}
