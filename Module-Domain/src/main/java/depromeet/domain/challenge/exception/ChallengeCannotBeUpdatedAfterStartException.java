package depromeet.domain.challenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ChallengeCannotBeUpdatedAfterStartException extends CustomException {
    public static final CustomException EXCEPTION =
            new ChallengeCannotBeUpdatedAfterStartException();

    private ChallengeCannotBeUpdatedAfterStartException() {
        super(CustomExceptionStatus.CHALLENGE_CANNOT_BE_UPDATED_AFTER_START);
    }
}
