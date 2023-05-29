package depromeet.api.domain.auth.controller;


import depromeet.api.domain.auth.dto.request.AuthRequest;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import depromeet.common.temp.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ResponseService responseService;
    private final KakaoAuthUseCase kakaoAuthUseCase;

    @PostMapping("/auth/kakao")
    public CommonResponse auth(@RequestBody AuthRequest reqAuth) {
        TokenInfo tokenInfo = kakaoAuthUseCase.execute(reqAuth);
        return responseService.getDataResponse(tokenInfo);
    }
}
