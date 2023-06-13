package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class UserNotParticipatedInChallengeException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotParticipatedInChallengeException();

    private UserNotParticipatedInChallengeException() {
        super(CustomExceptionStatus.PROGRESS_INFO_NOT_FOUND);
    }
}
