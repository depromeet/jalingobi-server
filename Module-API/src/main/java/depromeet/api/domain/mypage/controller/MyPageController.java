package depromeet.api.domain.mypage.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.usecase.GetMyPageUseCase;
import depromeet.api.domain.mypage.usecase.LogoutUseCase;
import depromeet.api.domain.mypage.usecase.UpdateProfileUseCase;
import depromeet.common.exception.CustomExceptionStatus;
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
    private final UpdateProfileUseCase updateProfileUseCase;
    private final LogoutUseCase logoutUseCase;

    @Operation(summary = "마이페이지 조회 API")
    @GetMapping()
    public Response<GetMyPageResponse> getUserProfile() {

        return ResponseService.getDataResponse(getMyPageUseCase.execute(getCurrentUserSocialId()));
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
}
