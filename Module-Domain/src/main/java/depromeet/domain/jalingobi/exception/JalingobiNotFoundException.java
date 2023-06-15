package depromeet.domain.jalingobi.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class JalingobiNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new JalingobiNotFoundException();

    private JalingobiNotFoundException() {
        super(CustomExceptionStatus.JALINGOBI_NOT_FOUND);
    }
}
