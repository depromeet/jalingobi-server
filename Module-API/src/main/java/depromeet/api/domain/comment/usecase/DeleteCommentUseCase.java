package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.response.DeleteCommentResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCommentUseCase {

    private final CommentAdaptor commentAdaptor;

    @Transactional
    public DeleteCommentResponse execute(String socialId, long commentId) {
        commentAdaptor.deleteComment(socialId, commentId);
        return DeleteCommentResponse.builder().commentId(commentId).build();
    }
}
