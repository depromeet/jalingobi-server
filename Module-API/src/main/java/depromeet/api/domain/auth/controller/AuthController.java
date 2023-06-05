package depromeet.api.domain.auth.controller;


import depromeet.api.domain.auth.dto.request.KakaoAuthRequest;
import depromeet.api.domain.auth.dto.response.KakaoAuthResponse;
import depromeet.api.domain.auth.usecase.AuthUseCase;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.api.util.CookieUtil;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final KakaoAuthUseCase kakaoAuthUseCase;
    private final AuthUseCase authUseCase;
    private final CookieUtil cookieUtil;

    @PostMapping("/auth/kakao")
    public CommonResponse authKakao(
            @RequestBody @Valid KakaoAuthRequest reqAuth, HttpServletResponse response) {

        KakaoAuthResponse kakaoAuthResponse = kakaoAuthUseCase.execute(reqAuth);
        response.addCookie(cookieUtil.setRefreshToken(kakaoAuthResponse.getRefreshToken()));
        return ResponseService.getDataResponse(kakaoAuthResponse);
    }

    @PostMapping("/auth/refresh")
    public CommonResponse refreshToken(HttpServletRequest request) {
        String refreshToken = cookieUtil.getCookie(request, "RefreshToken").getValue();
        String accessToken = authUseCase.checkRefreshToken(refreshToken);
        return ResponseService.getDataResponse(KakaoAuthResponse.of(refreshToken, accessToken));
    }
}
