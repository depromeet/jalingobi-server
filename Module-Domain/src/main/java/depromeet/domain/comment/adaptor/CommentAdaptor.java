package depromeet.domain.comment.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.comment.exception.CommentNotFoundException;
import depromeet.domain.comment.repository.CommentRepository;
import depromeet.domain.record.domain.Record;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class CommentAdaptor {

    private final CommentRepository commentRepository;

    public Comment addComment(Record record, String content) {
        Comment comment = Comment.create(record.getUserChallenge(), record, content);
        return commentRepository.save(comment);
    }

    public Comment findComment(long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
