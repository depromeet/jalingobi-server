package depromeet.api.domain.comment.usecase;


import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.mapper.CommentMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.comment.adaptor.CommentAdaptor;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateCommentUseCase {

    private final CommentAdaptor commentAdaptor;
    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;
    private final CommentMapper commentMapper;

    @Transactional
    public CreateCommentResponse execute(
            Long recordId, CreateCommentRequest request, String socialId) {

        User user = userAdaptor.findUser(socialId);
        Record record = recordAdaptor.findRecord(recordId);
        UserChallenge userChallenge =
                userChallengeAdaptor.findUserChallenge(record.getChallenge().getId(), user.getId());

        return commentMapper.toCreateCommentResponse(
                commentAdaptor.addComment(record, request.getContent(), userChallenge));
    }
}
