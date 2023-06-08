package depromeet.api.domain.record.usecase;


import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.mapper.RecordMapper;
import depromeet.api.domain.record.validator.RecordValidator;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CreateRecordUseCase {
    private final RecordMapper recordMapper;
    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final ChallengeAdaptor challengeAdaptor;

    private final RecordValidator recordValidator;

    public CreateRecordResponse execute(
            Long challengeRoomId, String socialId, CreateRecordRequest createRecordRequest) {

        User currentUser = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeRoomId);

        recordValidator.validateUnparticipatedChallenge(socialId, challenge);

        Record record =
                recordAdaptor.save(
                        recordMapper.toEntity(challenge, currentUser, createRecordRequest));

        return recordMapper.toCreateRecordResponse(record, currentUser);
    }
}
