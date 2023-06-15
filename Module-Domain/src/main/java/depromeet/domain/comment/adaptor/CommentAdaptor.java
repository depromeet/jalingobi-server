package depromeet.domain.comment.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.comment.exception.CommentNotFoundException;
import depromeet.domain.comment.repository.CommentRepository;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.exception.RecordNotFoundException;
import depromeet.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {

    private final RecordRepository recordRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment addComment(Long recordId, String content) {
        Record record =
                recordRepository
                        .findById(recordId)
                        .orElseThrow(() -> RecordNotFoundException.EXCEPTION);

        Comment comment = Comment.create(record.getUserChallenge(), record, content);

        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(String socialId, String content, Long commentId) {
        Comment savedComment =
                commentRepository
                        .findById(commentId)
                        .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        savedComment.isCommenter(socialId);
        savedComment.update(content);
    }
}
