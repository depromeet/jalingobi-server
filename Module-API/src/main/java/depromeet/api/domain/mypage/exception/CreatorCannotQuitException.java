package depromeet.api.domain.mypage.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CreatorCannotQuitException extends CustomException {
    public static final CustomException EXCEPTION = new CreatorCannotQuitException();

    private CreatorCannotQuitException() {
        super(CustomExceptionStatus.CREATOR_CANNOT_QUIT);
    }
}
