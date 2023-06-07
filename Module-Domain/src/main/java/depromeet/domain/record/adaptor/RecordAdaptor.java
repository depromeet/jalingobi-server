package depromeet.domain.record.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.exception.RecordNotFoundException;
import depromeet.domain.record.repository.RecordRepository;
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
}
