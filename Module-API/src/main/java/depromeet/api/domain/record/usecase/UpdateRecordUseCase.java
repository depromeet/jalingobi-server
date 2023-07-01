package depromeet.api.domain.record.usecase;


import depromeet.api.config.s3.S3UploadPresignedUrlService;
import depromeet.api.domain.record.dto.request.UpdateRecordRequest;
import depromeet.api.domain.record.validator.RecordValidator;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateRecordUseCase {
    @Value("${image.prefix}")
    private String prefix;

    private final RecordAdaptor recordAdaptor;
    private final RecordValidator recordValidator;
    private final S3UploadPresignedUrlService uploadPresignedUrlService;

    public void execute(Long recordId, String socialId, UpdateRecordRequest updateRecordRequest) {
        Record record = recordAdaptor.findRecord(recordId);

        recordValidator.validateCorrectUserRecord(record, socialId);
        recordValidator.validateProceedingChallenge(record.getChallenge());

        Optional<String> currentImgUrl = Optional.ofNullable(record.getImgUrl());
        if (currentImgUrl.isPresent()) {
            String imgUrl = currentImgUrl.get();
            String key = getKey(imgUrl);
            uploadPresignedUrlService.deleteImage(key);
        }
        UserChallenge userChallenge = record.getUserChallenge();
        userChallenge.removeCharge(record.getPrice());
        userChallenge.addCharge(updateRecordRequest.getPrice());

        record.updateRecord(
                updateRecordRequest.getPrice(),
                updateRecordRequest.getTitle(),
                updateRecordRequest.getContent(),
                updateRecordRequest.getImgUrl(),
                updateRecordRequest.getEvaluation());
    }

    private String getKey(String imgUrl) {
        String[] splitUrl = imgUrl.split(prefix);
        return splitUrl[1];
    }
}
