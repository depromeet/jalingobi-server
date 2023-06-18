package depromeet.api.domain.jalingobi.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.jalingobi.dto.request.GetSmallTalkRequest;
import depromeet.api.domain.jalingobi.dto.response.GetJalingobiResponse;
import depromeet.api.domain.jalingobi.dto.response.GetSmallTalkResponse;
import depromeet.api.domain.jalingobi.usecase.GetJalingobiUseCase;
import depromeet.api.domain.jalingobi.usecase.GetSmallTalkUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "자린고비", description = "자린고비 API")
@RestController
@RequestMapping("/jalingobi")
@RequiredArgsConstructor
public class JalingobiController {
    private final GetJalingobiUseCase getJalingobiUseCase;
    private final GetSmallTalkUseCase getSmallTalkUseCase;

    @Operation(summary = "자린고비 홈 조회 API")
    @GetMapping()
    public Response<GetJalingobiResponse> getJalingobiHome() {

        return ResponseService.getDataResponse(
                getJalingobiUseCase.execute(getCurrentUserSocialId()));
    }

    @Operation(summary = "자린고비 한마디 조회 API")
    @GetMapping()
    public Response<GetSmallTalkResponse> getJalingobiSmallTalk(
            @RequestBody GetSmallTalkRequest getSmallTalkRequest) {

        return ResponseService.getDataResponse(
                getSmallTalkUseCase.execute(getCurrentUserSocialId(), getSmallTalkRequest));
    }
}
