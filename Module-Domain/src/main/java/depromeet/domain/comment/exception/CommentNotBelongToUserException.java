package depromeet.domain.comment.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CommentNotBelongToUserException extends CustomException {
    public static final CustomException EXCEPTION = new CommentNotBelongToUserException();

    private CommentNotBelongToUserException() {
        super(CustomExceptionStatus.COMMENT_NOT_BELONG_TO_USER);
    }
}
