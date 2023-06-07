package depromeet.domain.category.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CategoryNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CategoryNotFoundException();

    private CategoryNotFoundException() {
        super(CustomExceptionStatus.CATEGORY_NOT_FOUND);
    }
}
