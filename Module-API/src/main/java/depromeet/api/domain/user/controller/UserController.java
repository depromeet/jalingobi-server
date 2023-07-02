package depromeet.api.domain.user.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.user.dto.response.GetUserInfoResponse;
import depromeet.api.domain.user.usecase.GetUserInfoUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자", description = "사용자 API")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final GetUserInfoUseCase getUserInfoUseCase;

    @GetMapping("/user/info")
    @Operation(summary = "사용자 정보 API", description = "사용자 정보를 가져옵니다.")
    public Response<GetUserInfoResponse> GetUserInfo() {
        return ResponseService.getDataResponse(
                getUserInfoUseCase.execute(getCurrentUserSocialId()));
    }
}
