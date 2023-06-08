package depromeet.api.domain.record.dto.response;


import depromeet.api.domain.record.dto.ChallengeRecord;
import depromeet.api.domain.record.dto.RecordComment;
import java.util.List;
import lombok.Data;

@Data
public class GetRecordResponse {

    private ChallengeRecord challengeRecord;

    private List<RecordComment> recordCommentList;
}
