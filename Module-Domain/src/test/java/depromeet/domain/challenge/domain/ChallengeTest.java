package depromeet.domain.challenge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import depromeet.domain.challenge.domain.keyword.ChallengeKeywords;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChallengeTest {

    /*
    챌린지 생성일 createdAt 이후 이틀 간 New 챌린지로 표시
    챌린지 시작일 startAt = createdAt + 7
    */
    @Test
    @DisplayName("New 챌린지 - 오늘 생성된 챌린지")
    public void isNewChallenge() {
        int period = 5;
        LocalDate startAt = LocalDate.now().plusDays(7);

        Challenge challenge =
                Challenge.createChallenge(
                        "마라탕 5만원 이하로 쓰기",
                        50000,
                        "/test.jpg",
                        mock(ChallengeKeywords.class),
                        30,
                        "socialId",
                        new ArrayList<>(),
                        mock(ChallengeCategories.class),
                        new Duration(period, startAt, startAt.plusDays(period)));

        assertThat(challenge.checkStatus(LocalDate.from(LocalDateTime.now())))
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

        Challenge challenge =
                Challenge.createChallenge(
                        "마라탕 5만원 이하로 쓰기",
                        50000,
                        "/test.jpg",
                        mock(ChallengeKeywords.class),
                        30,
                        "socialId",
                        new ArrayList<>(),
                        mock(ChallengeCategories.class),
                        new Duration(period, startAt, startAt.plusDays(period)));

        assertThat(challenge.checkStatus(LocalDate.from(createdAt)))
                .isEqualTo(StatusType.APPROACHING_DEADLINE.getName());
    }

    @Test
    @DisplayName("New 또는 마감임박에 해당하지 않는 챌린지")
    public void isNotThing() {
        int period = 5;
        LocalDate createdAt = LocalDate.now().minusDays(10);
        LocalDate startAt = createdAt.plusDays(7);

        Challenge challenge =
                Challenge.createChallenge(
                        "마라탕 5만원 이하로 쓰기",
                        50000,
                        "/test.jpg",
                        mock(ChallengeKeywords.class),
                        30,
                        "socialId",
                        new ArrayList<>(),
                        mock(ChallengeCategories.class),
                        new Duration(period, startAt, startAt.plusDays(period)));

        assertThat(challenge.checkStatus(LocalDate.from(createdAt)))
                .isEqualTo(StatusType.NOTHING.getName());
    }
}
