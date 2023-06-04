package depromeet.api.config.s3.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class BadFileExtensionException extends CustomException {
    public static final CustomException EXCEPTION = new BadFileExtensionException();

    private BadFileExtensionException() {
        super(CustomExceptionStatus.FILE_CONVERT_FAIL);
    }
}
