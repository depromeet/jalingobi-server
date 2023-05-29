package depromeet.common.exception.custom;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class InvalidJwtException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException() {
        super(CustomExceptionStatus.INVALID_JWT);
    }
}
