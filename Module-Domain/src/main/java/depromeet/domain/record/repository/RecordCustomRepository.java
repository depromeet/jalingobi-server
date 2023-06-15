package depromeet.domain.record.repository;


import depromeet.domain.record.domain.Record;
import java.util.List;

public interface RecordCustomRepository {
    List<Record> findMyRecordList(Long userId, Integer offset, Integer limit);

    Integer countMyRecordList(Long userId);

    List<Record> findChallengeRecordList(Long challengeId, Long recordId, Integer limit);

    List<Record> findFirstChallengeRecordList(Long challengeId, Integer limit);

    Integer countChallengeRecordList(Long challengeId);
}
