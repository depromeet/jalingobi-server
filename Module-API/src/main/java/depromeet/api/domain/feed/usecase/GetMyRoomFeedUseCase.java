package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetMyRoomFeedResponse;
import depromeet.common.annotation.UseCase;

@UseCase
public class GetMyRoomFeedUseCase {

    public GetMyRoomFeedResponse execute() {
        return new GetMyRoomFeedResponse(1, 1, 1, null);
    }
}
