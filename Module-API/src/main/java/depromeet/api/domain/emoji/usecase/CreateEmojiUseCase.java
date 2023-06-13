package depromeet.api.domain.emoji.usecase;


import depromeet.api.domain.emoji.dto.request.CreateEmojiRequest;
import depromeet.api.domain.emoji.dto.response.CreateEmojiResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateEmojiUseCase {

    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;
    private final RecordAdaptor recordAdaptor;

    @Transactional
    public CreateEmojiResponse execute(
            String socialId, Long recordId, CreateEmojiRequest createEmojiRequest) {
        User user = userAdaptor.findUser(socialId);
        Record record = recordAdaptor.findRecord(recordId);
        UserChallenge target =
                userChallengeAdaptor.findByUserChallenge(record.getChallenge(), user);

        target.reactEmoji(record, createEmojiRequest.getType());

        return new CreateEmojiResponse(target.getEmojiCounts(), createEmojiRequest.getType(), true);
    }
}
