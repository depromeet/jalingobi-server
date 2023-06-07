package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetMyFeedResponse;
import depromeet.common.annotation.UseCase;

@UseCase
public class GetMyFeedUseCase {

    public GetMyFeedResponse execute() {
        return new GetMyFeedResponse(1, 1, 1, null);
    }
}
