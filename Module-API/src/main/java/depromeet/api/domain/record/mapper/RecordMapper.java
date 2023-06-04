package depromeet.api.domain.record.mapper;


import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.domain.RecordEvaluation;
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
                createRecordRequest.getName(),
                createRecordRequest.getContent(),
                createRecordRequest.getImgUrl(),
                RecordEvaluation.getEnumTypeByValue(createRecordRequest.getEvaluation()));
    }

    public CreateRecordResponse toCreateRecordResponse(Record record) {
        return CreateRecordResponse.of(record);
    }
}
