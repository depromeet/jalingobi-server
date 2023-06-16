package depromeet.api.domain.emoji.usecase;


import depromeet.api.domain.emoji.dto.response.DeleteEmojiResponse;
import depromeet.common.annotation.UseCase;
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
public class DeleteEmojiUseCase {

    private final UserAdaptor userAdaptor;
    private final RecordAdaptor recordAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    @Transactional
    public DeleteEmojiResponse execute(String socialId, Long recordId, String type) {
        User source = userAdaptor.findUser(socialId);
        Record target = recordAdaptor.findRecord(recordId);
        UserChallenge userChallenge =
                userChallengeAdaptor.findByUserChallenge(target.getChallenge(), source);

        target.unReactEmoji(userChallenge, type);

        return new DeleteEmojiResponse(target.getEmojiCounts(), false);
    }
}
