package depromeet.api.domain.challenge.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.JoinChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.*;
import depromeet.api.domain.challenge.usecase.*;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.api.domain.challenge.usecase.DeleteChallengeUseCase;
import depromeet.api.domain.challenge.usecase.JoinChallengeUseCase;
import depromeet.api.domain.challenge.usecase.UpdateChallengeUseCase;
import depromeet.api.domain.challenge.validator.CreateChallengeValidator;
import depromeet.api.util.RandomNicknameGenerator;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import depromeet.domain.challenge.domain.CategoryType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
@Tag(name = "챌린지", description = "챌린지 CRUD API")
public class ChallengeController {

    private final CreateChallengeUseCase challengeUseCase;
    private final UpdateChallengeUseCase updateChallengeUseCase;
    private final DeleteChallengeUseCase deleteChallengeUseCase;
    private final JoinChallengeUseCase joinUserChallengeUseCase;
    private final GetChallengeUseCase getChallengeUseCase;
    private final CreateChallengeValidator createChallengeValidator;

    @Operation(summary = "챌린지 생성 API", description = "챌린지를 생성합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @PostMapping
    public Response<CreateChallengeResponse> createChallenge(
            @RequestBody @Valid CreateChallengeRequest challengeRequest) {
        createChallengeValidator.validate(challengeRequest);
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
    @PutMapping("/{challengeId}")
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
    @DeleteMapping("/{challengeId}")
    public Response<DeleteChallengeResponse> deleteChallenge(@PathVariable Long challengeId) {
        return ResponseService.getDataResponse(
                deleteChallengeUseCase.execute(challengeId, getCurrentUserSocialId()));
    }

    @PostMapping("/join/{challengeId}")
    @Operation(summary = "챌린지 참가 API", description = "유저가 해당 챌린지에 참가합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public CommonResponse joinChallenge(
            @PathVariable Long challengeId,
            @RequestBody @Valid JoinChallengeRequest joinChallengeRequest) {
        joinUserChallengeUseCase.execute(
                getCurrentUserSocialId(), joinChallengeRequest, challengeId);
        return ResponseService.getSuccessResponse();
    }

    @GetMapping("/{challengeId}")
    @Operation(summary = "챌린지 상세정보 API", description = "특정 챌린지의 상세정보를 보여줍니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<GetChallengeResponse> getChallenge(@PathVariable long challengeId) {
        return ResponseService.getDataResponse(getChallengeUseCase.execute(challengeId));
    }

    @GetMapping("/random-nickname")
    @Operation(summary = "랜덤 닉네임 API", description = "챌린지에서 사용할 랜덤 닉네임을 조회합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<CreateRandomNicknameResponse> createRandomNickname(
            @RequestParam String category) {
        CategoryType.of(category);
        return ResponseService.getDataResponse(RandomNicknameGenerator.generate());
    }
}
