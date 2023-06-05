package depromeet.domain.user.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class AccountAlreadyExist extends CustomException {

    public static final CustomException EXCEPTION = new AccountAlreadyExist();

    private AccountAlreadyExist() {
        super(CustomExceptionStatus.ACCOUNT_ALREADY_EXIST);
    }
}
