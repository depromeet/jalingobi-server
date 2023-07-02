package depromeet.api.domain.record.usecase;


import depromeet.api.domain.record.validator.RecordValidator;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
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
        recordValidator.validateProceedingChallenge(record.getChallenge());

        UserChallenge userChallenge = record.getUserChallenge();
        userChallenge.removeCharge(record.getPrice());

        recordAdaptor.deleteRecord(recordId);
    }
}
