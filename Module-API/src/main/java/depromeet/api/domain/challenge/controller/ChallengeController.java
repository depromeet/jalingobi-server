package depromeet.api.domain.challenge.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final CreateChallengeUseCase challengeUseCase;

    @PostMapping("/challenge")
    public CommonResponse createChallenge(
            @RequestBody @Valid CreateChallengeRequest ChallengeRequest) {
        return ResponseService.getDataResponse(
                challengeUseCase.execute(ChallengeRequest, getCurrentUserSocialId()));
    }
}
