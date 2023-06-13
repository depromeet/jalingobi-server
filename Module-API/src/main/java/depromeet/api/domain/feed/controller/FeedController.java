package depromeet.api.domain.feed.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.feed.dto.response.*;
import depromeet.api.domain.feed.usecase.*;
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

    private final GetMyChallengeListUseCase getMyChallengeListUseCase;
    private final GetChallengeProceedingInfoUseCase getChallengeProceedingInfoUseCase;
    private final GetMyRoomFeedUseCase getMyRoomFeedUseCase;
    private final GetChallengeFeedUseCase getChallengeFeedUseCase;

    @Operation(summary = "참여중인 챌린지 API", description = "참여중인 챌린지 방들의 정보를 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/my-list")
    public Response<GetMyChallengeListResponse> getMyChallengeList() {

        return ResponseService.getDataResponse(
                getMyChallengeListUseCase.execute(getCurrentUserSocialId()));
    }

    @Operation(summary = "챌린지 진행 정보 API", description = "챌린지의 진행 정보(목표 지출액, 현재 지출액 등)를 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/challenge/{challengeRoomId}/proceeding/info")
    public Response<GetChallengeProceedingInfoResponse> getChallengeProceedingInfo(
            @PathVariable("challengeRoomId") Long challengeRoomId) {

        return ResponseService.getDataResponse(
                getChallengeProceedingInfoUseCase.execute(
                        getCurrentUserSocialId(), challengeRoomId));
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
    public Response<GetMyRoomFeedResponse> getMyRoomFeed(@PathParam("offset") Integer offset) {

        return ResponseService.getDataResponse(
                getMyRoomFeedUseCase.execute(getCurrentUserSocialId(), offset));
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
            @PathParam("offsetRecordId") Long offsetRecordId) {

        return ResponseService.getDataResponse(
                getChallengeFeedUseCase.execute(
                        getCurrentUserSocialId(), challengeRoomId, offsetRecordId));
    }
}
