package depromeet.domain.comment.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.comment.exception.CommentNotFoundException;
import depromeet.domain.comment.repository.CommentRepository;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.exception.RecordNotFoundException;
import depromeet.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {

    private final RecordRepository recordRepository;
    private final CommentRepository commentRepository;

    public Comment addComment(long recordId, String content) {
        Record record =
                recordRepository
                        .findById(recordId)
                        .orElseThrow(() -> RecordNotFoundException.EXCEPTION);

        Comment comment = Comment.create(record.getUserChallenge(), record, content);

        return commentRepository.save(comment);
    }

    public void updateComment(String socialId, String content, long commentId) {
        Comment savedComment =
                commentRepository
                        .findById(commentId)
                        .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        savedComment.isCommenter(socialId);
        savedComment.update(content);
    }

    public void deleteComment(String socialId, long commentId) {
        Comment savedComment =
                commentRepository
                        .findById(commentId)
                        .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        savedComment.isCommenter(socialId);
        commentRepository.deleteById(commentId);
    }
}
