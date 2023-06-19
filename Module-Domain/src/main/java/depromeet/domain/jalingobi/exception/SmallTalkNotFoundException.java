package depromeet.domain.jalingobi.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class SmallTalkNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new SmallTalkNotFoundException();

    private SmallTalkNotFoundException() {
        super(CustomExceptionStatus.SMALL_TALK_NOT_FOUND);
    }
}
