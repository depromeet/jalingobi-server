package depromeet.api.domain.record.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidUserChallengeException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidUserChallengeException();

    private InvalidUserChallengeException() {
        super(CustomExceptionStatus.UNPARTICIPATED_CHALLENGE_USER);
    }
}
