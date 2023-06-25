package depromeet.api.domain.mypage.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.api.domain.mypage.dto.response.GetJalingobiImgResponse;
import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.dto.response.GetUserChallengesResponse;
import depromeet.api.domain.mypage.usecase.*;
import depromeet.api.domain.mypage.usecase.QuitChallengeUseCase;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "마이페이지 조회 API")
    @GetMapping()
    public Response<GetMyPageResponse> getUserProfile() {

        return ResponseService.getDataResponse(getMyPageUseCase.execute(getCurrentUserSocialId()));
    }

    @Operation(summary = "사용자의 전체 챌린지 목록 조회 API")
    @GetMapping("/challenges")
    public Response<GetUserChallengesResponse> getUserChallenges() {

        return ResponseService.getDataResponse(
                getUserChallengesUseCase.execute(getCurrentUserSocialId()));
    }

    @Operation(summary = "사용자 자린고비 이미지 URL 조회 API")
    @GetMapping("/jalingobi")
    public Response<GetJalingobiImgResponse> getJalingobiImgUrl() {

        return ResponseService.getDataResponse(
                getJalingobiImgUseCase.execute(getCurrentUserSocialId()));
    }

    @Operation(summary = "사용자 프로필을 수정하는 API")
    @PatchMapping("/profile")
    public Response<CustomExceptionStatus> updateUserProfile(
            @RequestBody @Valid UpdateProfileRequest updateRecordRequest) {

        updateProfileUseCase.execute(getCurrentUserSocialId(), updateRecordRequest);
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }

    @Operation(summary = "사용자 로그아웃 API")
    @PostMapping("/logout")
    public Response<CustomExceptionStatus> logout() {

        logoutUseCase.execute(getCurrentUserSocialId());
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }

    @Operation(summary = "사용자 탈퇴 API")
    @DeleteMapping("/withdrawal")
    public Response<CustomExceptionStatus> withdrawal() {

        withdrawalUseCase.execute(getCurrentUserSocialId());
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }

    @Operation(summary = "챌린지 나가기 API", description = "참여중인 챌린지를 나갑니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @DeleteMapping("/challenge/{challengeId}")
    public Response<CustomExceptionStatus> quitChallenge(
            @PathVariable("challengeId") Long challengeId) {

        quitChallengeUseCase.execute(getCurrentUserSocialId(), challengeId);
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }
}
