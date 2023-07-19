package depromeet.api.domain.record.dto.response;


import depromeet.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateRecordResponse {
    private Long id;

    public static CreateRecordResponse of(Record record) {

        return CreateRecordResponse.builder().id(record.getId()).build();
    }
}
