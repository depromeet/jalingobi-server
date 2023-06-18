package depromeet.api.util.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class ListNotNullException extends CustomException {
    public static final CustomException EXCEPTION = new ListNotNullException();

    private ListNotNullException() {
        super(CustomExceptionStatus.LIST_NOT_NULL_ERROR);
    }
}
