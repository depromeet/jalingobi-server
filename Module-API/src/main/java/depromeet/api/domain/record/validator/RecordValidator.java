package depromeet.api.domain.record.validator;


import depromeet.api.domain.record.exception.InvalidUserRecordException;
import depromeet.domain.record.domain.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordValidator {
    // 현재 챌린지에 유저가 가입했는지 확인하는 메서드
    // 지금 챌린지와 유저가 ManyToOne으로 설정되어 있어, ManyToMany로 수정된 뒤 코드 작성
    public void validateUnparticipatedChallenge(String socialId, Long challengeId) {}

    public void validateCorrectUserRecord(Record record, String socialId) {
        if (!record.getUser().isSameUser(socialId)) {
            throw InvalidUserRecordException.EXCEPTION;
        }
    }
}
