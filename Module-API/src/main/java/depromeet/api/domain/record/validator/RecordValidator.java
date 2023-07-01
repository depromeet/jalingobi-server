package depromeet.api.domain.record.validator;


import depromeet.api.domain.record.exception.InvalidChallengeStatusException;
import depromeet.api.domain.record.exception.InvalidUserChallengeException;
import depromeet.api.domain.record.exception.InvalidUserRecordException;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.domain.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordValidator {
    public void validateUnparticipatedChallenge(String socialId, Challenge challenge) {
        if (!challenge.isParticipateChallengeUser(socialId)) {
            throw InvalidUserChallengeException.EXCEPTION;
        }
    }

    public void validateCorrectUserRecord(Record record, String socialId) {
        if (!record.getUser().isSameUser(socialId)) {
            throw InvalidUserRecordException.EXCEPTION;
        }
    }

    public void validateProceedingChallenge(Challenge challenge) {
        if (!challenge.isProceeding()) {
            throw InvalidChallengeStatusException.EXCEPTION;
        }
    }
}
