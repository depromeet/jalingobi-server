package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidChallengeStartAtException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidChallengeStartAtException();

    private InvalidChallengeStartAtException() {
        super(CustomExceptionStatus.INVALID_CHALLENGE_START_AT);
    }
}
