package depromeet.api.util;


import depromeet.common.annotation.Util;
import depromeet.domain.challenge.domain.StatusType;
import java.time.LocalDate;

@Util
public class ChallengeStatusUtil {

    private boolean isNewChallenge(final LocalDate createdAt) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate beforeCreatedAt = LocalDate.from(createdAt.minusDays(1));
        final LocalDate untilNewDate = LocalDate.from(createdAt.plusDays(3));

        return currentDate.isAfter(beforeCreatedAt) && currentDate.isBefore(untilNewDate);
    }

    private boolean isApproachingDeadline(final LocalDate startDate) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate DeadlineStart = startDate.minusDays(4);
        final LocalDate afterStartDate = startDate.plusDays(1);

        return currentDate.isAfter(DeadlineStart) && currentDate.isBefore(afterStartDate);
    }

    public String checkStatusInChallengeFeed(final LocalDate createdAt, final LocalDate startedAt) {
        if (isNewChallenge(createdAt)) return StatusType.NEW.getName();
        if (isApproachingDeadline(startedAt)) return StatusType.APPROACHING_DEADLINE.getName();

        return StatusType.NOTHING.getName();
    }
}
