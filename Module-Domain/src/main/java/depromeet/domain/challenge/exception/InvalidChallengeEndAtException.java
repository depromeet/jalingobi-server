package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidChallengeEndAtException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidChallengeEndAtException();

    private InvalidChallengeEndAtException() {
        super(CustomExceptionStatus.INVALID_CHALLENGE_END_AT);
    }
}
