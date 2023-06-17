package depromeet.api.domain.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.user.dto.response.GetUserInfoResponse;
import depromeet.api.domain.user.usecase.GetUserInfoUseCase;
import depromeet.api.util.AuthenticationUtil;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Role;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(
        controllers = UserController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private GetUserInfoUseCase getUserInfoUseCase;

    private static MockedStatic<AuthenticationUtil> authenticationUtil;

    @BeforeAll
    public static void beforeALl() {
        authenticationUtil = mockStatic(AuthenticationUtil.class);
        given(AuthenticationUtil.getCurrentUserSocialId()).willReturn("socialId");
    }

    @AfterAll
    public static void afterAll() {
        authenticationUtil.close();
    }

    @Test
    @DisplayName("사용자 상세 정보_성공")
    void getUserInfo() throws Exception {
        // given
        GetUserInfoResponse response =
                GetUserInfoResponse.builder()
                        .id(15L)
                        .nickname("jalini")
                        .email("jalini@naver.com")
                        .imgUrl("imgUrl")
                        .platform(Platform.KAKAO.toString())
                        .role(Role.USER.getAuthority())
                        .score(2)
                        .build();

        given(getUserInfoUseCase.execute(any())).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/user/info"));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.id").value(response.getId()),
                        jsonPath("$.result.nickname").value(response.getNickname()),
                        jsonPath("$.result.email").value(response.getEmail()),
                        jsonPath("$.result.imgUrl").value(response.getImgUrl()),
                        jsonPath("$.result.platform").value(response.getPlatform()),
                        jsonPath("$.result.role").value(response.getRole()),
                        jsonPath("$.result.score").value(response.getScore()));
    }
}
