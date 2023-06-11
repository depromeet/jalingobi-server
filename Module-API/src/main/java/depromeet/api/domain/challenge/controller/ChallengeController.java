package depromeet.api.domain.challenge.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.api.domain.challenge.usecase.DeleteChallengeUseCase;
import depromeet.api.domain.challenge.usecase.UpdateChallengeUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final CreateChallengeUseCase challengeUseCase;
    private final UpdateChallengeUseCase updateChallengeUseCase;
    private final DeleteChallengeUseCase deleteChallengeUseCase;

    @PostMapping("/challenge")
    public CommonResponse createChallenge(
            @RequestBody @Valid CreateChallengeRequest challengeRequest) {
        return ResponseService.getDataResponse(
                challengeUseCase.execute(challengeRequest, getCurrentUserSocialId()));
    }

    @PutMapping("/challenge/{challengeId}")
    public CommonResponse updateChallenge(
            @PathVariable Long challengeId, @RequestBody UpdateChallengeRequest challengeRequest) {
        return ResponseService.getDataResponse(
                updateChallengeUseCase.execute(challengeRequest, getCurrentUserSocialId()));
    }

    @DeleteMapping("/challenge/{challengeId}")
    public CommonResponse deleteChallenge(@PathVariable Long challengeId) {
        deleteChallengeUseCase.execute(challengeId, getCurrentUserSocialId());
        return ResponseService.getSuccessResponse();
    }
}
