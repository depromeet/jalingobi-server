package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.response.DeleteCommentResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.record.adaptor.RecordAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCommentUseCase {

    private final CommentAdaptor commentAdaptor;
    private final RecordAdaptor recordAdaptor;

    @Transactional
    public DeleteCommentResponse execute(String socialId, long commentId, long recordId) {
        recordAdaptor.findRecord(recordId);

        Comment comment = commentAdaptor.findComment(commentId);
        comment.isCommenter(socialId);

        commentAdaptor.deleteComment(commentId);

        return DeleteCommentResponse.builder().commentId(commentId).build();
    }
}
