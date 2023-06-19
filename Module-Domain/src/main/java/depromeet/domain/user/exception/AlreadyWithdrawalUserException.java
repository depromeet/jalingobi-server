package depromeet.domain.user.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class AlreadyWithdrawalUserException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyWithdrawalUserException();

    private AlreadyWithdrawalUserException() {
        super(CustomExceptionStatus.ALREADY_WITHDRAWAL_USER);
    }
}
