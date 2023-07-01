package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.mapper.RecordListMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class GetChallengeFeedUseCase {

    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;
    private final RecordListMapper recordListMapper;

    private final Integer LIMIT = 20;

    @Transactional(readOnly = true)
    public GetChallengeFeedResponse execute(String socialId, Long challengeId, Long recordId) {
        User user = userAdaptor.findUser(socialId);
        UserChallenge userChallenge =
                userChallengeAdaptor.validateParticipatedInChallenge(user.getId(), challengeId);

        List<Record> recordList = recordAdaptor.findChallengeList(challengeId, recordId, LIMIT);
        Integer total = recordAdaptor.countChallengeRecordList(challengeId);

        return recordListMapper.toGetChallengeFeedResponse(
                recordList, userChallenge.getId(), total, LIMIT);
    }
}
