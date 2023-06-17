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
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "내 거지방", description = "내 거지방 API")
@RequestMapping("/challenge")
@RequiredArgsConstructor
@RestController
public class FeedController {

    private final GetMyChallengeListUseCase getMyChallengeListUseCase;
    private final GetChallengeProceedingInfoUseCase getChallengeProceedingInfoUseCase;
    private final GetMyRoomFeedUseCase getMyRoomFeedUseCase;
    private final GetChallengeFeedUseCase getChallengeFeedUseCase;

    @Operation(summary = "참여중인 챌린지 API", description = "참여중인 챌린지들의 정보를 가져옵니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/my-list")
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
    @GetMapping("/{challengeId}/proceeding/info")
    public Response<GetChallengeProceedingInfoResponse> getChallengeProceedingInfo(
            @PathVariable("challengeId") Long challengeId) {

        return ResponseService.getDataResponse(
                getChallengeProceedingInfoUseCase.execute(getCurrentUserSocialId(), challengeId));
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
    @GetMapping("/my-room/feed")
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
    @GetMapping("/{challengeId}/feed")
    public Response<GetChallengeFeedResponse> getChallengeFeed(
            @PathVariable("challengeId") Long challengeId,
            @PathParam("offsetRecordId") Long offsetRecordId) {

        return ResponseService.getDataResponse(
                getChallengeFeedUseCase.execute(
                        getCurrentUserSocialId(), challengeId, offsetRecordId));
    }
}
