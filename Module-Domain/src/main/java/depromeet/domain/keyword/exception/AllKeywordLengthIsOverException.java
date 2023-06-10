package depromeet.domain.keyword.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class AllKeywordLengthIsOverException extends CustomException {

    public static final CustomException EXCEPTION = new AllKeywordLengthIsOverException();

    private AllKeywordLengthIsOverException() {
        super(CustomExceptionStatus.ALL_KEYWORD_LENGTH_IS_OVER);
    }
}
