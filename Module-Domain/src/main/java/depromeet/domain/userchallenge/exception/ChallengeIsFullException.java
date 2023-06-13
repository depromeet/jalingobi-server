package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeIsFullException extends CustomException {
    public static final CustomException EXCEPTION = new ChallengeIsFullException();

    private ChallengeIsFullException() {
        super(CustomExceptionStatus.CHALLENGE_IS_FULL);
    }
}
