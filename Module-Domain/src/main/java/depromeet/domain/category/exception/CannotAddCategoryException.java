package depromeet.domain.category.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CannotAddCategoryException extends CustomException {

    public static final CustomException EXCEPTION = new CannotAddCategoryException();

    private CannotAddCategoryException() {
        super(CustomExceptionStatus.CANNOT_ADD_CATEGORY);
    }
}
