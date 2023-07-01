package depromeet.api.domain.auth.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.auth.dto.request.KakaoAuthRequest;
import depromeet.api.domain.auth.dto.response.KakaoAuthResponse;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.api.domain.auth.usecase.RefreshTokenUseCase;
import depromeet.api.util.CookieUtil;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@WebMvcTest(
        controllers = AuthController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private KakaoAuthUseCase kakaoAuthUseCase;

    @MockBean private CookieUtil cookieUtil;

    @MockBean private RefreshTokenUseCase refreshTokenUseCase;

    @Test
    public void authKakao_인증_성공() throws Exception {
        // given
        KakaoAuthRequest input = new KakaoAuthRequest("idToken", "accessToken");

        String body = objectMapper.writeValueAsString(input);

        KakaoAuthResponse kakaoAuthResponse = new KakaoAuthResponse("refreshToken", "accessToken");
        given(kakaoAuthUseCase.execute(any())).willReturn(kakaoAuthResponse);

        Cookie cookie = new Cookie("RefreshToken", kakaoAuthResponse.getRefreshToken());
        given(cookieUtil.setRefreshToken(any())).willReturn(cookie);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/auth/kakao").contentType(MediaType.APPLICATION_JSON).content(body));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(cookie().value("RefreshToken", kakaoAuthResponse.getRefreshToken()))
                .andExpect(
                        jsonPath("$.result.accessToken").value(kakaoAuthResponse.getAccessToken()));
    }

    @Test
    @DisplayName("Refresh Token을 이용한 Access Token 재발급")
    public void refreshTokenTest() throws Exception {
        String refreshToken = "refresh-token";
        String newAccessToken = "new-access-token";
        Cookie cookie = new Cookie("REFRESH_TOKEN", refreshToken);

        MockHttpServletRequestBuilder requestBuilder =
                post("/auth/refresh")
                        .header("REFRESH-TOKEN", refreshToken)
                        .contentType(MediaType.APPLICATION_JSON);

        when(cookieUtil.getCookie(any(), anyString())).thenReturn(cookie);
        when(refreshTokenUseCase.execute(anyString())).thenReturn(newAccessToken);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.result.accessToken").value(newAccessToken));
    }
}
