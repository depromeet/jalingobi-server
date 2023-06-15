package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateCommentUseCase {

    private final CommentAdaptor commentAdaptor;

    @Transactional
    public void execute(UpdateCommentRequest request, String socialId, Long commentId) {
        commentAdaptor.updateComment(socialId, request.getContent(), commentId);
    }
}
