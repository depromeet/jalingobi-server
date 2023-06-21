package depromeet.domain.config;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.repository.ChallengeRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SchedulingConfig {

    private final ChallengeRepository challengeRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void openChallenge() {
        log.info("openChallenge");
        LocalDate now = LocalDate.now();
        List<Challenge> challenges = challengeRepository.findStartChallenge(now, false);
        challenges.stream().forEach(challenge -> challenge.open());
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void closeChallenge() {
        log.info("closeChallenge");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Challenge> challenges = challengeRepository.findEndChallenge(yesterday, true);

        challenges.stream().forEach(challenge -> challenge.close());

        challenges.stream()
                .flatMap(challenge -> challenge.getUserChallenges().stream())
                .forEach(userChallenge -> userChallenge.checkResult());
    }
}
