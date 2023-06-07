package depromeet.api.domain.record.usecase;


import depromeet.api.domain.record.validator.RecordValidator;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class DeleteRecordUseCase {
    private final RecordAdaptor recordAdaptor;
    private final RecordValidator recordValidator;

    public void execute(Long recordId, String socialId) {
        Record record = recordAdaptor.findRecord(recordId);

        recordValidator.validateCorrectUserRecord(record, socialId);

        recordAdaptor.deleteRecord(recordId);
    }
}
