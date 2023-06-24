package depromeet.domain.userchallenge.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserChallengeTest {

    @Test
    @DisplayName("챌린지 종료시 조건 검증 - 성공")
    void checkResult_Success() {
        // given
        User user = User.builder().score(1).build();

        Duration duration = Duration.builder().period(10).build();
        Challenge challenge = Challenge.builder().duration(duration).build();

        UserChallenge userChallenge =
                UserChallenge.builder()
                        .user(user)
                        .challenge(challenge)
                        .goalCharge(10000)
                        .currentCharge(7000)
                        .status(Status.PROCEEDING)
                        .build();

        for (long i = 0; i < 7; i++) {
            Record record = Record.builder().build();
            record.setCreatedAt(LocalDateTime.now().plusDays(i));
            userChallenge.getRecords().add(record);
        }

        // when
        userChallenge.endChallenge();

        // then
        Assertions.assertEquals(Status.SUCCESS, userChallenge.getStatus());
        Assertions.assertEquals(2, user.getScore());
    }

    @Test
    @DisplayName("챌린지 종료시 조건 검증 - 실패")
    void checkResult_Failure() {
        // given
        User user = User.builder().score(1).build();

        Duration duration = Duration.builder().period(10).build();
        Challenge challenge = Challenge.builder().duration(duration).build();

        UserChallenge userChallenge =
                UserChallenge.builder()
                        .user(user)
                        .challenge(challenge)
                        .goalCharge(10000)
                        .currentCharge(7000)
                        .status(Status.PROCEEDING)
                        .build();

        for (long i = 0; i < 5; i++) {
            Record record = Record.builder().build();
            record.setCreatedAt(LocalDateTime.now().plusDays(i));
            userChallenge.getRecords().add(record);
        }

        // when
        userChallenge.endChallenge();

        // then
        Assertions.assertEquals(Status.FAILURE, userChallenge.getStatus());
        Assertions.assertEquals(0, user.getScore());
    }
}
