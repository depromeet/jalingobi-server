package depromeet.api.domain.challenge.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.dto.response.UpdateChallengeResponse;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.api.domain.challenge.usecase.DeleteChallengeUseCase;
import depromeet.api.domain.challenge.usecase.UpdateChallengeUseCase;
import depromeet.api.domain.userchallenge.dto.request.CreateUserChallengeRequest;
import depromeet.api.domain.userchallenge.usecase.CreateUserChallengeUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final CreateChallengeUseCase challengeUseCase;
    private final UpdateChallengeUseCase updateChallengeUseCase;
    private final DeleteChallengeUseCase deleteChallengeUseCase;
    private final CreateUserChallengeUseCase createUserChallengeUseCase;

    @Operation(summary = "챌린지 생성 API", description = "챌린지를 생성합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @PostMapping("/challenge")
    public Response<CreateChallengeResponse> createChallenge(
            @RequestBody @Valid CreateChallengeRequest challengeRequest) {
        return ResponseService.getDataResponse(
                challengeUseCase.execute(challengeRequest, getCurrentUserSocialId()));
    }

    @Operation(summary = "챌린지 수정 API", description = "챌린지를 수정합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @PutMapping("/challenge/{challengeId}")
    public Response<UpdateChallengeResponse> updateChallenge(
            @PathVariable Long challengeId, @RequestBody UpdateChallengeRequest challengeRequest) {
        return ResponseService.getDataResponse(
                updateChallengeUseCase.execute(challengeRequest, getCurrentUserSocialId()));
    }

    @Operation(summary = "챌린지 삭제 API", description = "챌린지를 삭제합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @DeleteMapping("/challenge/{challengeId}")
    public CommonResponse deleteChallenge(@PathVariable Long challengeId) {
        deleteChallengeUseCase.execute(challengeId, getCurrentUserSocialId());
        return ResponseService.getSuccessResponse();
    }

    @PostMapping("/challenge/join/{challengeId}")
    public CommonResponse joinChallenge(
            @PathVariable Long challengeId,
            @RequestBody @Valid CreateUserChallengeRequest createUserChallengeRequest) {
        createUserChallengeUseCase.execute(
                getCurrentUserSocialId(), createUserChallengeRequest, challengeId);
        return ResponseService.getSuccessResponse();
    }
}
