package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.ChallengeProgress;
import depromeet.api.domain.feed.dto.Feed;
import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.common.annotation.UseCase;
import java.util.ArrayList;
import java.util.List;

@UseCase
public class GetFeedUseCase {

    public GetChallengeFeedResponse execute() {
        ChallengeProgress challengeProgress = new ChallengeProgress(1, 1, 1, 1);
        List<Feed> feedList = new ArrayList<>();
        GetChallengeFeedResponse getChallengeFeedResponse =
                new GetChallengeFeedResponse(challengeProgress, feedList);
        return getChallengeFeedResponse;
    }
}
