package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.mapper.CommentMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateCommentUseCase {

    private final CommentAdaptor commentAdaptor;
    private final RecordAdaptor recordAdaptor;
    private final CommentMapper commentMapper;

    @Transactional
    public CreateCommentResponse execute(Long recordId, CreateCommentRequest request) {
        Record record = recordAdaptor.findRecord(recordId);
        return commentMapper.toCreateCommentResponse(
                commentAdaptor.addComment(record, request.getContent()));
    }
}
