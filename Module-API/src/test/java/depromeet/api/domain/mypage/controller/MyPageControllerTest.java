package depromeet.api.domain.mypage.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.api.domain.mypage.usecase.GetMyPageUseCase;
import depromeet.api.domain.mypage.usecase.LogoutUseCase;
import depromeet.api.domain.mypage.usecase.UpdateProfileUseCase;
import depromeet.api.util.AuthenticationUtil;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
        controllers = MyPageController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
class MyPageControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private GetMyPageUseCase getMyPageUseCase;
    @MockBean private UpdateProfileUseCase updateProfileUseCase;
    @MockBean private LogoutUseCase logoutUseCase;

    private static MockedStatic<AuthenticationUtil> authenticationUtil;

    @Autowired private ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        authenticationUtil = mockStatic(AuthenticationUtil.class);
    }

    @AfterAll
    public static void afterAll() {
        authenticationUtil.close();
    }

    @Test
    @DisplayName("[PATCH] 프로필 수정")
    public void UpdateRecordTest() throws Exception {
        // given
        UpdateProfileRequest updateProfileRequest =
                UpdateProfileRequest.builder().nickName("냠냠").profileImgUrl("index.png").build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.patch("/mypage/profile", 1)
                        .with(csrf())
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(updateProfileRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        String socialId = "socialId";

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        mockMvc.perform(requestBuilder).andDo(print()).andExpectAll(status().isOk());
    }
}
