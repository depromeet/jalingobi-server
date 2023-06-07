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
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
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

        CreateChallengeRequest request =
                CreateChallengeRequest.builder()
                        .category(categories)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .imageUrl("test.jpg")
                        .hashtag("#마라탕 #5만원챌린지")
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
}
