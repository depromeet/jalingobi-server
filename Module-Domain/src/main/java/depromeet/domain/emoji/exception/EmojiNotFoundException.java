package depromeet.domain.emoji.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class EmojiNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new EmojiNotFoundException();

    private EmojiNotFoundException() {
        super(CustomExceptionStatus.EMOJI_NOT_FOUND);
    }
}
