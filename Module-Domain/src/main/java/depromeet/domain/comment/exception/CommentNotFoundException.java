package depromeet.domain.comment.exception;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;

public class CommentNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(CustomExceptionStatus.COMMENT_NOT_FOUND);
    }
}
