package depromeet.domain.emoji.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class DuplicatedEmojiException extends CustomException {
    public static final CustomException EXCEPTION = new DuplicatedEmojiException();

    private DuplicatedEmojiException() {
        super(CustomExceptionStatus.DUPLICATED_EMOJI);
    }
}
