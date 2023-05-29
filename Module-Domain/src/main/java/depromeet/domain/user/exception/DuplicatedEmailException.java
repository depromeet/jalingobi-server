package depromeet.domain.user.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class DuplicatedEmailException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedEmailException();

    private DuplicatedEmailException() {
        super(CustomExceptionStatus.DUPLICATED_EMAIL);
    }
}
