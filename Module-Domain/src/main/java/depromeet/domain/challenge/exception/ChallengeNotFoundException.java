package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ChallengeNotFoundException();

    private ChallengeNotFoundException() {
        super(CustomExceptionStatus.CHALLENGE_NOT_FOUND);
    }
}
