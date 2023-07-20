package depromeet.domain.record.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class NoMatchEmojiException extends CustomException {

    public static final CustomException EXCEPTION = new NoMatchEmojiException();

    private NoMatchEmojiException() {
        super(CustomExceptionStatus.NO_MATCH_EMOJI);
    }
}
