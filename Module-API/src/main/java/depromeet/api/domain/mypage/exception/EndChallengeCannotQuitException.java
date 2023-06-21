package depromeet.api.domain.mypage.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class EndChallengeCannotQuitException extends CustomException {
    public static final CustomException EXCEPTION = new EndChallengeCannotQuitException();

    private EndChallengeCannotQuitException() {
        super(CustomExceptionStatus.END_CHALLENGE_CANNOT_QUIT);
    }
}
