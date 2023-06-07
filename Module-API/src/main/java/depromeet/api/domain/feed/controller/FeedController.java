package depromeet.api.domain.feed.controller;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.usecase.GetFeedUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedController {

    private final GetFeedUseCase getFeedUseCase;

    @GetMapping("/challenge/my-room")
    public void getMyFeed() {}

    @Operation(summary = "챌린지 방 입장 API", description = "챌린지 방 입장시 목표 금액 현황 및 피드들을 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/{challengeRoomId}")
    public Response<GetChallengeFeedResponse> getChallengeFeed(
            @PathVariable("challengeRoomId") Integer challengeRoomId) {

        return ResponseService.getDataResponse(getFeedUseCase.execute());
    }
}
