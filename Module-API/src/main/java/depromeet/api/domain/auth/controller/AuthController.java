package depromeet.api.domain.auth.controller;


import depromeet.api.domain.auth.dto.request.AuthRequest;
import depromeet.api.domain.auth.service.AuthService;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import depromeet.common.temp.TokenInfo;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ResponseService responseService;
    private final KakaoAuthUseCase kakaoAuthUseCase;
    private final AuthService authUseCase;

    @PostMapping("/auth/kakao")
    public CommonResponse auth(@RequestBody AuthRequest reqAuth) {
        TokenInfo tokenInfo = kakaoAuthUseCase.execute(reqAuth);
        return responseService.getDataResponse(tokenInfo);
    }

    @PostMapping("/auth/refresh")
    public CommonResponse refreshToken(
            @RequestHeader("AUTHORIZATION") String token,
            @RequestHeader("REFRESH-TOKEN") String refreshToken,
            HttpServletResponse httpServletResponse) {
        String accessToken = authUseCase.checkRefreshToken(token, refreshToken);
        httpServletResponse.setHeader("AUTHORIZATION", accessToken);
        return responseService.getSuccessResponse();
    }
}
