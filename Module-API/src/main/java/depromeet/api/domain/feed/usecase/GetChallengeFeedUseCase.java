package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.common.annotation.UseCase;

@UseCase
public class GetChallengeFeedUseCase {

    public GetChallengeFeedResponse execute() {
        return new GetChallengeFeedResponse(1, 1, 1, null);
    }
}
