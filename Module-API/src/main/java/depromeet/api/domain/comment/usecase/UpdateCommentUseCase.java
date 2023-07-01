package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.UpdateCommentResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.record.adaptor.RecordAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateCommentUseCase {

    private final CommentAdaptor commentAdaptor;
    private final RecordAdaptor recordAdaptor;

    @Transactional
    public UpdateCommentResponse execute(
            UpdateCommentRequest request, String socialId, Long commentId, long recordId) {
        recordAdaptor.findRecord(recordId);

        Comment comment = commentAdaptor.findComment(commentId);
        comment.isCommenter(socialId);
        comment.update(request.getContent());

        return UpdateCommentResponse.builder().commentId(commentId).build();
    }
}
