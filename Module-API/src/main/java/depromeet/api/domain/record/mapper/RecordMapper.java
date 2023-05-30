package depromeet.api.domain.record.mapper;


import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class RecordMapper {

    public Record toEntity(
            Long challengeRoomId, Long userId, CreateRecordRequest createRecordRequest) {
        return Record.createRecord(
                challengeRoomId,
                userId,
                createRecordRequest.getPrice(),
                createRecordRequest.getName(),
                createRecordRequest.getContent(),
                createRecordRequest.getImgUrl(),
                createRecordRequest.getEvaluation());
    }

    public CreateRecordResponse toCreateRecordResponse(Record record, User user) {
        return CreateRecordResponse.of(record, user);
    }
}
