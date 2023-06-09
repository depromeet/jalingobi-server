package depromeet.api.domain.mypage.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.api.domain.mypage.dto.response.GetJalingobiImgResponse;
import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.dto.response.GetUserChallengesResponse;
import depromeet.api.domain.mypage.dto.response.QuitChallengeResponse;
import depromeet.api.domain.mypage.usecase.*;
import depromeet.api.domain.mypage.usecase.QuitChallengeUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "마이페이지", description = "마이페이지 API")
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final GetMyPageUseCase getMyPageUseCase;

    private final GetUserChallengesUseCase getUserChallengesUseCase;
    private final GetJalingobiImgUseCase getJalingobiImgUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;
    private final LogoutUseCase logoutUseCase;
    private final WithdrawalUseCase withdrawalUseCase;
    private final QuitChallengeUseCase quitChallengeUseCase;

    @GetMapping()
    @Operation(summary = "마이페이지 조회 API")
    public Response<GetMyPageResponse> getUserProfile() {

        return ResponseService.getDataResponse(getMyPageUseCase.execute(getCurrentUserSocialId()));
    }

    @GetMapping("/challenges")
    @Operation(summary = "사용자의 전체 챌린지 목록 조회 API")
    public Response<GetUserChallengesResponse> getUserChallenges() {

        return ResponseService.getDataResponse(
                getUserChallengesUseCase.execute(getCurrentUserSocialId()));
    }

    @GetMapping("/jalingobi")
    @Operation(summary = "사용자 자린고비 이미지 URL 조회 API")
    public Response<GetJalingobiImgResponse> getJalingobiImgUrl() {

        return ResponseService.getDataResponse(
                getJalingobiImgUseCase.execute(getCurrentUserSocialId()));
    }

    @PatchMapping("/profile")
    @Operation(summary = "사용자 프로필을 수정하는 API")
    public Response updateUserProfile(
            @RequestBody @Valid UpdateProfileRequest updateRecordRequest) {

        updateProfileUseCase.execute(getCurrentUserSocialId(), updateRecordRequest);
        return ResponseService.getSuccessResponse();
    }

    @PostMapping("/logout")
    @Operation(summary = "사용자 로그아웃 API")
    public Response logout() {

        logoutUseCase.execute(getCurrentUserSocialId());
        return ResponseService.getSuccessResponse();
    }

    @DeleteMapping("/withdrawal")
    @Operation(summary = "사용자 탈퇴 API")
    public Response withdrawal() {

        withdrawalUseCase.execute(getCurrentUserSocialId());
        return ResponseService.getSuccessResponse();
    }

    @DeleteMapping("/challenge/{challengeId}")
    @Operation(summary = "챌린지 나가기 API", description = "참여중인 챌린지를 나갑니다.")
    public Response<QuitChallengeResponse> quitChallenge(
            @PathVariable("challengeId") Long challengeId) {

        quitChallengeUseCase.execute(getCurrentUserSocialId(), challengeId);
        return ResponseService.getDataResponse(new QuitChallengeResponse(challengeId));
    }
}
