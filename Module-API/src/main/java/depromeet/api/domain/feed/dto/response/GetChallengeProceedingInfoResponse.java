package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.ChallengeProgress;
import lombok.Data;

@Data
public class GetChallengeProceedingInfoResponse {
    private ChallengeProgress challengeProgress;
}
