package depromeet.domain.keyword.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CannotAddKeywordException extends CustomException {

    public static final CustomException EXCEPTION = new CannotAddKeywordException();

    private CannotAddKeywordException() {
        super(CustomExceptionStatus.CANNOT_ADD_KEYWORD);
    }
}
