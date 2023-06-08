package depromeet.domain.keyword.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class KeywordFormatException extends CustomException {

    public static final CustomException EXCEPTION = new KeywordFormatException();

    private KeywordFormatException() {
        super(CustomExceptionStatus.INVALID_KEYWORD_FORMAT);
    }
}
