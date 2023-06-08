package depromeet.api.domain.feed.controller;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.dto.response.GetChallengeRoomResponse;
import depromeet.api.domain.feed.dto.response.GetMyFeedResponse;
import depromeet.api.domain.feed.dto.response.GetMyRoomResponse;
import depromeet.api.domain.feed.usecase.GetChallengeFeedUseCase;
import depromeet.api.domain.feed.usecase.GetChallengeRoomUseCase;
import depromeet.api.domain.feed.usecase.GetMyFeedUseCase;
import depromeet.api.domain.feed.usecase.GetMyRoomUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedController {

    private final GetChallengeRoomUseCase getChallengeRoomUseCase;

    private final GetChallengeFeedUseCase getChallengeFeedUseCase;
    private final GetMyRoomUseCase getMyRoomUseCase;

    private final GetMyFeedUseCase getMyFeedUseCase;

    @Operation(summary = "내 방 입장 API", description = "내 방 입장시 참여중인 챌린지 방 목록 및 내 피드들을 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/my-room")
    public Response<GetMyRoomResponse> getMyRoom() {

        return ResponseService.getDataResponse(getMyRoomUseCase.execute());
    }

    @Operation(summary = "내 방 피드 API", description = "내 방에 있는 기록들을 20개씩 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/my-room/feed")
    public Response<GetMyFeedResponse> getMyFeed(@PathParam("page") Integer page) {

        return ResponseService.getDataResponse(getMyFeedUseCase.execute());
    }

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
    public Response<GetChallengeRoomResponse> getChallengeRoom(
            @PathVariable("challengeRoomId") Long challengeRoomId) {

        return ResponseService.getDataResponse(getChallengeRoomUseCase.execute());
    }

    @Operation(summary = "챌린지 방 피드 API", description = "챌린지 방에 있는 기록들을 20개씩 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/{challengeRoomId}/feed")
    public Response<GetChallengeFeedResponse> getChallengeFeed(
            @PathVariable("challengeRoomId") Long challengeRoomId,
            @PathParam("page") Integer page) {

        return ResponseService.getDataResponse(getChallengeFeedUseCase.execute());
    }
}
