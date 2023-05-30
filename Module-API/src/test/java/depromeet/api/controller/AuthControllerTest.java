package depromeet.api.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.auth.controller.AuthController;
import depromeet.api.domain.auth.service.AuthService;
import depromeet.api.domain.auth.usecase.KakaoAuthUseCase;
import depromeet.common.response.ResponseService;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Autowired MockMvc mockMvc;

    @MockBean ResponseService responseService;

    @MockBean KakaoAuthUseCase kakaoAuthUseCase;

    @MockBean AuthService authUseCase;

    @Test
    @DisplayName("Refresh Token을 이용한 Access Token 재발급")
    public void refreshTokenTest() throws Exception {
        String token = "access-token";
        String refreshToken = "refresh-token";
        String newAccessToken = "new-access-token";

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/auth/refresh")
                        .header("AUTHORIZATION", token)
                        .header("REFRESH-TOKEN", refreshToken)
                        .contentType(MediaType.APPLICATION_JSON);

        when(authUseCase.checkRefreshToken(anyString(), anyString())).thenReturn(newAccessToken);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(status().isOk(), header().string("AUTHORIZATION", newAccessToken));
    }
}
