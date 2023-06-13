package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class UnparticipatedChallengeUserException extends CustomException {
    public static final CustomException EXCEPTION = new UnparticipatedChallengeUserException();

    private UnparticipatedChallengeUserException() {
        super(CustomExceptionStatus.UNPARTICIPATED_CHALLENGE_USER);
    }
}
