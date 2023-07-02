package depromeet.api.domain.guest.controller;


import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.api.domain.guest.usecase.ExperienceGuestModeUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GuestController {

    private final ExperienceGuestModeUseCase experienceGuestModeUseCase;

    @GetMapping("/guest")
    @Operation(summary = "게스트 모드 API", description = "게스트 모드로 진입하기 위한 access token을 발급합니다.")
    public Response<ExperienceGuestModeResponse> experienceGuestMode() {

        return ResponseService.getDataResponse(experienceGuestModeUseCase.execute());
    }
}
