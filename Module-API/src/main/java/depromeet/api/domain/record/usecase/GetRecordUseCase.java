package depromeet.api.domain.record.usecase;


import depromeet.api.domain.record.dto.response.GetRecordResponse;
import depromeet.api.domain.record.mapper.RecordMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class GetRecordUseCase {

    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    private final RecordMapper recordMapper;

    @Transactional(readOnly = true)
    public GetRecordResponse execute(String socialId, Long challengeId, Long recordId) {
        User user = userAdaptor.findUser(socialId);
        UserChallenge userChallenge =
                userChallengeAdaptor.validateParticipatedInChallenge(user.getId(), challengeId);

        Record record = recordAdaptor.findRecord(recordId);

        return recordMapper.toGetRecordResponse(record, userChallenge.getId());
    }
}
