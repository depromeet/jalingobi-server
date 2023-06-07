package depromeet.api.domain.record.mapper;


import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.domain.Evaluation;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class RecordMapper {

    public Record toEntity(
            Challenge challenge, User user, CreateRecordRequest createRecordRequest) {
        return Record.createRecord(
                challenge,
                user,
                createRecordRequest.getPrice(),
                createRecordRequest.getTitle(),
                createRecordRequest.getContent(),
                createRecordRequest.getImgUrl(),
                Evaluation.getEnumTypeByValue(createRecordRequest.getEvaluation()));
    }

    public CreateRecordResponse toCreateRecordResponse(Record record, User user) {
        return CreateRecordResponse.of(record, user);
    }
}
