package depromeet.api.domain.guest.controller;


import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.api.domain.guest.usecase.ExperienceGuestModeUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GuestController {

    private final ExperienceGuestModeUseCase experienceGuestModeUseCase;

    @Operation(summary = "게스트 모드 API", description = "게스트 모드로 진입하기 위한 access token을 발급합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/guest")
    public Response<ExperienceGuestModeResponse> experienceGuestMode() {

        return ResponseService.getDataResponse(experienceGuestModeUseCase.execute());
    }
}
