package depromeet.domain.challenge.exception;

import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeNotBelongToUserException extends CustomException {
    public static final CustomException EXCEPTION = new ChallengeNotBelongToUserException();

    private ChallengeNotBelongToUserException() {
        super(CustomExceptionStatus.CHALLENGE_NOT_BELONG_TO_USER);
    }

}
