package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.UpdateCommentResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateCommentUseCase {

    private final CommentAdaptor commentAdaptor;

    @Transactional
    public UpdateCommentResponse execute(
            UpdateCommentRequest request, String socialId, Long commentId) {
        commentAdaptor.updateComment(socialId, request.getContent(), commentId);
        return UpdateCommentResponse.builder().commentId(commentId).build();
    }
}
