package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.ChallengeProgress;
import depromeet.api.domain.feed.dto.Feed;
import depromeet.api.domain.feed.dto.response.GetChallengeRoomResponse;
import depromeet.common.annotation.UseCase;
import java.util.ArrayList;
import java.util.List;

@UseCase
public class GetChallengeRoomUseCase {

    public GetChallengeRoomResponse execute() {
        ChallengeProgress challengeProgress = new ChallengeProgress(1, 1, 1, 1);
        List<Feed> feedList = new ArrayList<>();
        return new GetChallengeRoomResponse(challengeProgress, feedList);
    }
}
