package depromeet.api.domain.auth.controller;


import depromeet.api.domain.auth.dto.request.KakaoAuthRequest;
import depromeet.api.domain.auth.dto.response.KakaoAuthResponse;
import depromeet.api.domain.auth.dto.response.TokenResponse;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.api.domain.auth.usecase.RefreshTokenUseCase;
import depromeet.api.util.CookieUtil;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "인증 API")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final KakaoAuthUseCase kakaoAuthUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final CookieUtil cookieUtil;

    @Operation(
            summary = "KAKAO 소셜로그인 API",
            description = "사용자 인증 이후, access[body]/refresh[cookie] token을 발급합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Set-Cookie에 RefreshToken={토큰값} 형태로 RefreshToken이 들어있습니다.",
                        headers = {
                            @Header(name = "Set-Cookie", description = "RefreshToken={value}")
                        }),
                @ApiResponse(
                        responseCode = "400",
                        description = "유효하지 않은 토큰으로 요청하거나, 다른 플랫폼에 해당 이메일로 가입된 계정이 있을 경우",
                        content = @Content())
            })
    @PostMapping("/kakao")
    public Response<KakaoAuthResponse> authKakao(
            @RequestBody @Valid KakaoAuthRequest reqAuth, HttpServletResponse response) {

        KakaoAuthResponse kakaoAuthResponse = kakaoAuthUseCase.execute(reqAuth);
        response.addCookie(cookieUtil.setRefreshToken(kakaoAuthResponse.getRefreshToken()));
        return ResponseService.getDataResponse(kakaoAuthResponse);
    }

    @Operation(summary = "토큰 reissue API", description = "Refresh Token을 이용해 Access Token을 재발급합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "유효하지 않은 Refresh Token으로 요청했을 때",
                        content = @Content())
            })
    @PostMapping("/refresh")
    public Response<TokenResponse> refreshToken(
            HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = cookieUtil.getCookie(request, "RefreshToken").getValue();
        TokenResponse tokenResponse = refreshTokenUseCase.execute(refreshToken);

        if (tokenResponse.isExistRefreshToken())
            response.addCookie(cookieUtil.setRefreshToken(tokenResponse.getRefreshToken()));
        return ResponseService.getDataResponse(tokenResponse);
    }
}
