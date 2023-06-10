package depromeet.api.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.challenge.controller.ChallengeController;
import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.dto.response.UpdateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.api.domain.challenge.usecase.DeleteChallengeUseCase;
import depromeet.api.domain.challenge.usecase.UpdateChallengeUseCase;
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

        CreateChallengeRequest request =
                CreateChallengeRequest.builder()
                        .category(categories)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .imageUrl("test.jpg")
                        .keywords(keywords)
                        .availableCount(30)
                        // .challengeRule(rules) List<String>을 받도록 수정할 예정
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
}
