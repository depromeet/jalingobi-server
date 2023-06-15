package depromeet.api.domain.comment.usecase;


import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCommentUseCase {

    private final CommentAdaptor commentAdaptor;

    @Transactional
    public void execute(String socialId, long commentId) {
        commentAdaptor.deleteComment(socialId, commentId);
    }
}
