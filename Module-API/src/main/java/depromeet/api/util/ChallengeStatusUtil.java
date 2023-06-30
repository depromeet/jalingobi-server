package depromeet.api.util;


import depromeet.common.annotation.Util;
import depromeet.domain.challenge.domain.StatusType;
import java.time.LocalDate;

@Util
public class ChallengeStatusUtil {

    private boolean isNewChallenge(final LocalDate createdAt) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate startNew = LocalDate.from(createdAt.minusDays(1));
        final LocalDate endNew = LocalDate.from(createdAt.plusDays(3));

        return currentDate.isAfter(startNew) && currentDate.isBefore(endNew);
    }

    private boolean isApproachingDeadline(final LocalDate startDate) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate startDeadline = startDate.minusDays(4);

        return currentDate.isAfter(startDeadline) && currentDate.isBefore(startDate);
    }

    public String checkStatusInChallengeFeed(final LocalDate createdAt, final LocalDate startedAt) {
        if (isNewChallenge(createdAt)) return StatusType.NEW.getName();
        if (isApproachingDeadline(startedAt)) return StatusType.APPROACHING_DEADLINE.getName();

        return StatusType.NOTHING.getName();
    }
}
