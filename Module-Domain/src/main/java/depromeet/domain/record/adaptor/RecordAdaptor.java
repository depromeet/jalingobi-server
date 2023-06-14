package depromeet.domain.record.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.exception.RecordNotFoundException;
import depromeet.domain.record.repository.RecordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecordAdaptor {
    private final RecordRepository recordRepository;

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public Record findRecord(Long recordId) {
        return recordRepository
                .findById(recordId)
                .orElseThrow(() -> RecordNotFoundException.EXCEPTION);
    }

    public void deleteRecord(Long recordId) {
        recordRepository.deleteById(recordId);
    }

    public List<Record> findMyRecordList(Long userId, Integer offset, Integer limit) {
        return recordRepository.findMyRecordList(userId, offset, limit);
    }

    public Integer countMyRecordList(Long userId) {
        return recordRepository.countMyRecordList(userId);
    }

    public List<Record> findChallengeList(Long challengeId, Long recordId, Integer limit) {
        if (recordId == 0) return recordRepository.findFirstChallengeRecordList(challengeId, limit);
        else return recordRepository.findChallengeRecordList(challengeId, recordId, limit);
    }

    public Integer countChallengeRecordList(Long challengeId) {
        return recordRepository.countChallengeRecordList(challengeId);
    }
}
