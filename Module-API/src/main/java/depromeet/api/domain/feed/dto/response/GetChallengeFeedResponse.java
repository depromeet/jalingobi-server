package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.ChallengeProgress;
import depromeet.api.domain.feed.dto.Feed;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetChallengeFeedResponse {

    private ChallengeProgress challengeProgress;

    private List<Feed> feedList;
}
