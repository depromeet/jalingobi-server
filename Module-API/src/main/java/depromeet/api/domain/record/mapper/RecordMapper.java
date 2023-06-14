package depromeet.api.domain.record.mapper;


import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.dto.response.GetRecordResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.domain.Evaluation;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class RecordMapper {

    public Record toEntity(
            Challenge challenge,
            User user,
            UserChallenge userChallenge,
            CreateRecordRequest createRecordRequest) {
        return Record.createRecord(
                challenge,
                user,
                userChallenge,
                createRecordRequest.getPrice(),
                createRecordRequest.getTitle(),
                createRecordRequest.getContent(),
                createRecordRequest.getImgUrl(),
                Evaluation.getEnumTypeByValue(createRecordRequest.getEvaluation()));
    }

    public CreateRecordResponse toCreateRecordResponse(Record record, User user) {
        return CreateRecordResponse.of(record, user);
    }

    public GetRecordResponse toGetRecordResponse(Record record, Long myUserChallengeId) {
        return GetRecordResponse.of(record, myUserChallengeId);
    }
}
