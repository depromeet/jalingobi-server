package depromeet.api.domain.guest.controller;


import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.api.domain.guest.usecase.ExperienceGuestModeUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GuestController {

    private final ExperienceGuestModeUseCase experienceGuestModeUseCase;

    @GetMapping("/guest")
    public Response<ExperienceGuestModeResponse> experienceGuestMode() {

        return ResponseService.getDataResponse(experienceGuestModeUseCase.execute());
    }
}
