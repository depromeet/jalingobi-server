package depromeet.api.domain.mypage.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.mypage.dto.request.UpdateProfileRequest;
import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.usecase.*;
import depromeet.api.domain.mypage.usecase.QuitChallengeUseCase;
import depromeet.api.util.AuthenticationUtil;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.userchallenge.domain.Status;
import java.util.HashMap;
import java.util.Map;
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
    @MockBean private GetUserChallengesUseCase getUserChallengesUseCase;
    @MockBean private GetJalingobiImgUseCase getJalingobiImgUseCase;
    @MockBean private UpdateProfileUseCase updateProfileUseCase;
    @MockBean private LogoutUseCase logoutUseCase;
    @MockBean private WithdrawalUseCase withdrawalUseCase;
    @MockBean private QuitChallengeUseCase quitChallengeUseCase;

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
    @DisplayName("[GET] 마이페이지 조회")
    public void getUserProfileTest() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";
        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);

        Map<String, Long> userChallengeResult = new HashMap<>();
        userChallengeResult.put(Status.SUCCESS.getValue(), 1L);
        userChallengeResult.put(Status.PROCEEDING.getValue(), 2L);
        userChallengeResult.put(Status.COMPLETED.getValue(), 3L);

        GetMyPageResponse getMyPageResponse =
                GetMyPageResponse.builder()
                        .social(social)
                        .profile(profile)
                        .notification(false)
                        .userChallengeResult(userChallengeResult)
                        .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/mypage")
                        .with(csrf())
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(getMyPageUseCase.execute(any())).thenReturn(getMyPageResponse);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.social.id").value(getMyPageResponse.getSocial().getId()),
                        jsonPath("$.result.profile.nickname")
                                .value(getMyPageResponse.getProfile().getNickname()),
                        jsonPath("$.result.profile.email")
                                .value(getMyPageResponse.getProfile().getEmail()),
                        jsonPath("$.result.profile.imgUrl")
                                .value(getMyPageResponse.getProfile().getImgUrl()),
                        jsonPath("$.result.notification")
                                .value(getMyPageResponse.getNotification()),
                        jsonPath("$.result.userChallengeResult.성공")
                                .value(
                                        getMyPageResponse
                                                .getUserChallengeResult()
                                                .get(Status.SUCCESS.getValue())),
                        jsonPath("$.result.userChallengeResult.참가중")
                                .value(
                                        getMyPageResponse
                                                .getUserChallengeResult()
                                                .get(Status.PROCEEDING.getValue())),
                        jsonPath("$.result.userChallengeResult.완료")
                                .value(
                                        getMyPageResponse
                                                .getUserChallengeResult()
                                                .get(Status.COMPLETED.getValue())));
    }

    @Test
    @DisplayName("[PATCH] 프로필 수정")
    public void UpdateRecordTest() throws Exception {
        // given
        UpdateProfileRequest updateProfileRequest =
                UpdateProfileRequest.builder().nickName("냠냠").profileImgUrl("index.png").build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.patch("/mypage/profile")
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
