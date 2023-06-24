package depromeet.api.util;

import static org.assertj.core.api.Assertions.assertThat;

import depromeet.domain.challenge.domain.StatusType;
import depromeet.domain.challenge.repository.ChallengeData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChallengeStatusUtilTest {

    ChallengeStatusUtil challengeStatusUtil = new ChallengeStatusUtil();

    /*
    챌린지 생성일 createdAt 이후 이틀 간 New 챌린지로 표시
    챌린지 시작일 startAt = createdAt + 7
    */
    @Test
    @DisplayName("New 챌린지 - 오늘 생성된 챌린지")
    public void isNewChallenge() {
        int period = 5;
        LocalDate startAt = LocalDate.now().plusDays(7);

        ChallengeData challenge =
                new ChallengeData(
                        1L,
                        "마라탕 5만원 이하로 쓰기",
                        10,
                        30,
                        "/test.jpg",
                        50000,
                        startAt,
                        LocalDateTime.now(),
                        period);

        assertThat(
                        challengeStatusUtil.checkStatusInChallengeFeed(
                                LocalDate.from(LocalDateTime.now()), challenge.getStartAt()))
                .isEqualTo(StatusType.NEW.getName());
    }

    /*
    챌린지 시작일 startAt의 3일 전부터 마감임박 표시
    챌린지 시작일 당일에는 참여 불가
     */
    @Test
    @DisplayName("마감임박 챌린지")
    public void isApproachingDeadline() {
        int period = 5;
        LocalDate createdAt = LocalDate.now().minusDays(5);
        LocalDate startAt = createdAt.plusDays(7);

        ChallengeData challenge =
                new ChallengeData(
                        1L,
                        "마라탕 5만원 이하로 쓰기",
                        10,
                        30,
                        "/test.jpg",
                        50000,
                        startAt,
                        LocalDateTime.now(),
                        period);

        assertThat(
                        challengeStatusUtil.checkStatusInChallengeFeed(
                                LocalDate.from(createdAt), challenge.getStartAt()))
                .isEqualTo(StatusType.APPROACHING_DEADLINE.getName());
    }

    @Test
    @DisplayName("New 또는 마감임박에 해당하지 않는 챌린지")
    public void isNotThing() {
        int period = 5;
        LocalDate createdAt = LocalDate.now().minusDays(10);
        LocalDate startAt = createdAt.plusDays(7);

        ChallengeData challenge =
                new ChallengeData(
                        1L,
                        "마라탕 5만원 이하로 쓰기",
                        10,
                        30,
                        "/test.jpg",
                        50000,
                        startAt,
                        LocalDateTime.now(),
                        period);

        assertThat(
                        challengeStatusUtil.checkStatusInChallengeFeed(
                                LocalDate.from(createdAt), challenge.getStartAt()))
                .isEqualTo(StatusType.NOTHING.getName());
    }
}
