package depromeet.api.domain.challenge.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.JoinChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.*;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.api.domain.challenge.usecase.*;
import depromeet.api.util.AuthenticationUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

@WebMvcTest(
        controllers = ChallengeController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class ChallengeControllerTest {

    @Autowired MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @MockBean CreateChallengeUseCase challengeUseCase;

    @MockBean UpdateChallengeUseCase updateChallengeUseCase;

    @MockBean DeleteChallengeUseCase deleteChallengeUseCase;

    @MockBean JoinChallengeUseCase createUserChallengeUseCase;

    @MockBean GetChallengeUseCase getChallengeUseCase;

    ChallengeMapper challengeMapper = new ChallengeMapper();

    private static MockedStatic<AuthenticationUtil> authenticationUtil;

    @BeforeAll
    public static void beforeALl() {
        authenticationUtil = mockStatic(AuthenticationUtil.class);
    }

    @AfterAll
    public static void afterAll() {
        authenticationUtil.close();
    }

    @Test
    @DisplayName("챌린지 생성")
    public void createChallengeTest() throws Exception {
        List<String> categories = new ArrayList<>();
        categories.add("식비");

        List<String> keywords = new ArrayList<>();
        keywords.add("#마라탕");
        keywords.add("#5만원챌린지");

        List<String> rules = new ArrayList<>();
        rules.add("광고 금지");

        CreateChallengeRequest request =
                CreateChallengeRequest.builder()
                        .category(categories)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .imageUrl("test.jpg")
                        .keywords(keywords)
                        .availableCount(30)
                        .challengeRule(rules)
                        .period(15)
                        .startAt(LocalDate.of(2023, 6, 1))
                        .endAt(LocalDate.of(2023, 6, 15))
                        .build();

        String socialId = "socialId";

        CreateChallengeResponse response = challengeMapper.toCreateChallengeResponse(1L);

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(challengeUseCase.execute(any(), anyString())).thenReturn(response);

        String challengeRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/challenge")
                                .content(challengeRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(status().isOk(), jsonPath("$.result.id").value(response.getId()));
    }

    @Test
    @DisplayName("챌린지 수정")
    public void updateChallengeTest() throws Exception {
        String socialId = "socialId";

        List<String> categories = new ArrayList<>();
        categories.add("식비");

        List<String> keywords = new ArrayList<>();
        keywords.add("#마라탕");
        keywords.add("#5만원챌린지");

        List<String> challengeRules = new ArrayList<>();
        challengeRules.add("욕설 금지");

        UpdateChallengeRequest request =
                UpdateChallengeRequest.builder()
                        .ChallengeId(1L)
                        .categories(categories)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .imgUrl("test.jpg")
                        .keywords(keywords)
                        .availableCount(30)
                        .rules(challengeRules)
                        .period(15)
                        .startAt(LocalDate.of(2023, 6, 1))
                        .endAt(LocalDate.of(2023, 6, 15))
                        .build();

        UpdateChallengeResponse response = challengeMapper.toUpdateChallengeResponse(request);

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(updateChallengeUseCase.execute(any(), anyString())).thenReturn(response);

        mockMvc.perform(
                        put("/challenge/{challengeId}", 1L)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.categories").value(response.getCategories()),
                        jsonPath("$.result.title").value(response.getTitle()),
                        jsonPath("$.result.price").value(response.getPrice()),
                        jsonPath("$.result.imgUrl").value(response.getImgUrl()),
                        jsonPath("$.result.keywords").value(response.getKeywords()),
                        jsonPath("$.result.availableCount").value(response.getAvailableCount()),
                        jsonPath("$.result.rules").value(response.getRules()),
                        jsonPath("$.result.period").value(response.getPeriod()),
                        jsonPath("$.result.startAt").value(String.valueOf(response.getStartAt())),
                        jsonPath("$.result.endAt").value(String.valueOf(response.getEndAt())));

        verify(updateChallengeUseCase, times(1))
                .execute(any(UpdateChallengeRequest.class), anyString());
    }

    @Test
    @DisplayName("챌린지 삭제")
    public void deleteChallengeTest() throws Exception {
        String socialId = "socialId";

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        willDoNothing().given(deleteChallengeUseCase).execute(anyLong(), anyString());

        mockMvc.perform(delete("/challenge/{challengeId}", 1L))
                .andDo(print())
                .andExpectAll(status().isOk());

        verify(deleteChallengeUseCase, times(1)).execute(anyLong(), anyString());
    }

    @Test
    @DisplayName("챌린지 참가")
    public void joinChallengeTest() throws Exception {
        String socialId = "socialId";
        JoinChallengeRequest createUserChallengeRequest =
                JoinChallengeRequest.builder()
                        .nickname("닉네임")
                        .currentCharge(10000)
                        .imgUrl("/test.jpg")
                        .build();

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        willDoNothing().given(createUserChallengeUseCase).execute(anyString(), any(), anyLong());

        mockMvc.perform(
                        post("/challenge/join/{challengeId}", 1L)
                                .content(
                                        objectMapper.writeValueAsString(createUserChallengeRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(status().isOk());

        verify(createUserChallengeUseCase, times(1)).execute(anyString(), any(), anyLong());
    }

    @Test
    @DisplayName("챌린지 상세 조회")
    public void getChallengeTest() throws Exception {
        List<String> categories = new ArrayList<>();
        categories.add("식비");

        List<String> keywords = new ArrayList<>();
        keywords.add("#마라탕");
        keywords.add("#5만원챌린지");

        List<String> rules = new ArrayList<>();
        rules.add("광고 금지");

        List<ProfileResponse> profileResponses = new ArrayList<>();
        profileResponses.add(mock(ProfileResponse.class));

        LocalDate startAt = LocalDate.of(2023, 6, 1);
        LocalDate endAt = LocalDate.of(2023, 6, 7);

        GetChallengeResponse response =
                GetChallengeResponse.builder()
                        .challengeId(1L)
                        .categories(categories)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .challengeImgUrl("/test.jpg")
                        .keywords(keywords)
                        .headCount(new HeadCountResponse(30, 25))
                        .participantsInfo(profileResponses)
                        .rules(rules)
                        .isRecruiting(false)
                        .dateInfo(new DateInfoResponse(7, startAt, endAt))
                        .build();

        when(getChallengeUseCase.execute(anyLong())).thenReturn(response);

        mockMvc.perform(get("/challenge/{challengeId}", 1L))
                .andDo(print())
                .andExpectAll(status().isOk());

        verify(getChallengeUseCase, times(1)).execute(anyLong());
    }

    @Test
    @DisplayName("챌린지에서 사용할 랜덤 닉네임 생성")
    public void createRandomNicknameTest() throws Exception {
        mockMvc.perform(get("/challenge/random-nickname").param("category", "식비"))
                .andDo(print())
                .andExpectAll(status().isOk(), jsonPath("$.result.nickname").isNotEmpty());
    }
}
