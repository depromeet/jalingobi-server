package depromeet.domain.userchallenge.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class NegativeChargeException extends CustomException {
    public static final CustomException EXCEPTION = new NegativeChargeException();

    private NegativeChargeException() {
        super(CustomExceptionStatus.CHARGE_CANNOT_BE_NEGATIVE);
    }
}
