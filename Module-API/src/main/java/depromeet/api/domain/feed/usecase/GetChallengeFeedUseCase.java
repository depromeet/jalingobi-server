package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.mapper.RecordListMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional(readOnly = true)
public class GetChallengeFeedUseCase {

    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;
    private final RecordListMapper recordListMapper;

    private final Integer LIMIT = 20;

    public GetChallengeFeedResponse execute(String socialId, Long challengeRoomId, Long recordId) {
        User user = userAdaptor.findUser(socialId);
        userChallengeAdaptor.validateParticipatedInChallenge(user.getId(), challengeRoomId);

        List<Record> recordList =
                recordAdaptor.findChallengeRoomList(challengeRoomId, recordId, LIMIT);
        Integer total = recordAdaptor.countChallengeRecordList(challengeRoomId);
        return recordListMapper.toGetChallengeFeedResponse(recordList, total, LIMIT);
    }
}
