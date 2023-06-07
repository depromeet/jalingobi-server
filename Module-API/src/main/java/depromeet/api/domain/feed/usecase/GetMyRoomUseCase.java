package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetMyRoomResponse;
import depromeet.common.annotation.UseCase;

@UseCase
public class GetMyRoomUseCase {

    public GetMyRoomResponse execute() {
        return new GetMyRoomResponse();
    }
}
