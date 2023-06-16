package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.mapper.CommentMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateCommentUseCase {

    private final CommentAdaptor commentAdaptor;
    private final CommentMapper commentMapper;

    @Transactional
    public CreateCommentResponse execute(Long recordId, CreateCommentRequest request) {
        return commentMapper.toCreateCommentResponse(
                commentAdaptor.addComment(recordId, request.getContent()));
    }
}
