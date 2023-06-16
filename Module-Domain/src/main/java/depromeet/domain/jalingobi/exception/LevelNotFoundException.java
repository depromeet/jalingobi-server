package depromeet.domain.jalingobi.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class LevelNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new LevelNotFoundException();

    private LevelNotFoundException() {
        super(CustomExceptionStatus.JALINGOBI_LEVEL_NOT_FOUND);
    }
}
