package depromeet.api.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.challenge.controller.ChallengeController;
import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.api.util.AuthenticationUtil;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import depromeet.domain.challenge.domain.Category;
import depromeet.domain.rule.domain.Rule;
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

    @MockBean ResponseService responseService;

    @MockBean CreateChallengeUseCase challengeUseCase;

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
        CreateChallengeRequest request =
                CreateChallengeRequest.builder()
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .hashtag("#마라탕 #5만원챌린지")
                        .build();

        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.builder().content("잡담 금지").build());
        rules.add(Rule.builder().content("개인 정보 올리지 않기").build());

        CreateChallengeResponse response =
                CreateChallengeResponse.builder()
                        .id(1L)
                        .category(Category.FOOD.getName())
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .imgUrl("test.jpg")
                        .hashtag("#마라탕 #5만원챌린지")
                        .availableCount(30)
                        .challengeRules(rules)
                        .period(15)
                        .startAt(LocalDate.of(2023, 6, 1))
                        .endAt(LocalDate.of(2023, 6, 15))
                        .build();

        Response result = new Response();
        result.setResult(response);
        String socialId = "socialId";

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(responseService.getDataResponse(any())).thenReturn(result);

        String challengeRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/challenge")
                                .content(challengeRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.id").value(response.getId()),
                        jsonPath("$.result.category").value(response.getCategory()),
                        jsonPath("$.result.title").value(response.getTitle()),
                        jsonPath("$.result.price").value(response.getPrice()),
                        jsonPath("$.result.imgUrl").value(response.getImgUrl()),
                        jsonPath("$.result.hashtag").value(response.getHashtag()),
                        jsonPath("$.result.availableCount").value(response.getAvailableCount()),
                        jsonPath("$.result.period").value(response.getPeriod()),
                        jsonPath("$.result.startAt").value(String.valueOf(response.getStartAt())),
                        jsonPath("$.result.endAt").value(String.valueOf(response.getEndAt())));
    }
}
