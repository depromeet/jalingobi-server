package depromeet.api.domain.challenge.validator;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.domain.challenge.exception.InvalidChallengeEndAtException;
import depromeet.domain.challenge.exception.InvalidChallengeStartAtException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateChallengeValidator {

    private static final int RECRUIT_PERIOD = 7;

    public void validate(Object target) {
        CreateChallengeRequest createChallengeRequest = (CreateChallengeRequest) target;
        LocalDate startAt = LocalDate.now().plusDays(RECRUIT_PERIOD);
        int period = createChallengeRequest.getPeriod();

        if (!createChallengeRequest.getStartAt().isEqual(startAt)) {
            log.info("챌린지 시작일자 {}가 유효하지 않음", createChallengeRequest.getStartAt());
            throw InvalidChallengeStartAtException.EXCEPTION;
        }

        if (!createChallengeRequest.getEndAt().isEqual(startAt.plusDays(period))) {
            log.info("챌린지 종료일자 {}가 유효하지 않음", createChallengeRequest.getEndAt());
            throw InvalidChallengeEndAtException.EXCEPTION;
        }
    }
}
