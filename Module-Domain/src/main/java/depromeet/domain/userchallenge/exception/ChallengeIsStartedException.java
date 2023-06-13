package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeIsStartedException extends CustomException {
    public static final CustomException EXCEPTION = new ChallengeIsStartedException();

    private ChallengeIsStartedException() {
        super(CustomExceptionStatus.CHALLENGE_IS_STARTED);
    }
}
