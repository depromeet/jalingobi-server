package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeCannotBeDeletedAfterStartException extends CustomException {
    public static final CustomException EXCEPTION =
            new ChallengeCannotBeDeletedAfterStartException();

    private ChallengeCannotBeDeletedAfterStartException() {
        super(CustomExceptionStatus.CHALLENGE_CANNOT_BE_DELETED_AFTER_START);
    }
}
