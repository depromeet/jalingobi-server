package depromeet.api.domain.feed.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.feed.dto.request.GetChallengeFeedRequest;
import depromeet.api.domain.feed.dto.request.GetMyRoomFeedRequest;
import depromeet.api.domain.feed.dto.response.*;
import depromeet.api.domain.feed.usecase.*;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
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

    @GetMapping("/my-list")
    @Operation(summary = "참여중인 챌린지 API", description = "참여중인 챌린지들의 정보를 가져옵니다.")
    public Response<GetMyChallengeListResponse> getMyChallengeList() {

        return ResponseService.getDataResponse(
                getMyChallengeListUseCase.execute(getCurrentUserSocialId()));
    }

    @GetMapping("/{challengeId}/proceeding/info")
    @Operation(summary = "챌린지 진행 정보 API", description = "챌린지의 진행 정보(목표 지출액, 현재 지출액 등)를 가져옵니다.")
    public Response<GetChallengeProceedingInfoResponse> getChallengeProceedingInfo(
            @PathVariable("challengeId") Long challengeId) {

        return ResponseService.getDataResponse(
                getChallengeProceedingInfoUseCase.execute(getCurrentUserSocialId(), challengeId));
    }

    @GetMapping("/my-room/feed")
    @Operation(summary = "내 방 피드 API", description = "내 방에 있는 기록들을 20개씩 가져옵니다.")
    public Response<GetMyRoomFeedResponse> getMyRoomFeed(
            @Valid @ParameterObject GetMyRoomFeedRequest getMyRoomFeedRequest) {

        return ResponseService.getDataResponse(
                getMyRoomFeedUseCase.execute(
                        getCurrentUserSocialId(), getMyRoomFeedRequest.getOffset()));
    }

    @GetMapping("/{challengeId}/feed")
    @Operation(summary = "챌린지 방 피드 API", description = "챌린지 방에 있는 기록들을 20개씩 가져옵니다.")
    public Response<GetChallengeFeedResponse> getChallengeFeed(
            @PathVariable("challengeId") Long challengeId,
            @Valid @ParameterObject GetChallengeFeedRequest getChallengeFeedRequest) {

        return ResponseService.getDataResponse(
                getChallengeFeedUseCase.execute(
                        getCurrentUserSocialId(),
                        challengeId,
                        getChallengeFeedRequest.getOffsetRecordId()));
    }
}
