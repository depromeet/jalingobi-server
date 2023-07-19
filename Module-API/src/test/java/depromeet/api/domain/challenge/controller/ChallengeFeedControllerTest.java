package depromeet.api.domain.challenge.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.challenge.usecase.GetChallengeInfiniteScrollFeedUseCase;
import depromeet.domain.challenge.domain.ChallengeSlice;
import depromeet.domain.challenge.domain.Image;
import depromeet.domain.challenge.repository.ChallengeData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
        controllers = ChallengeFeedController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class ChallengeFeedControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean GetChallengeInfiniteScrollFeedUseCase getChallengeInfiniteScrollFeedUseCase;

    @Test
    @DisplayName("챌린지 탐색")
    public void searchChallenge() throws Exception {
        String category = "FOOD";
        String filter = "all";
        String sortType = "price";
        ChallengeData challengeData =
                new ChallengeData(
                        1L,
                        "마라탕 10만원 이하로 쓰기",
                        2,
                        10,
                        new Image("/test.png", "/thumb/test.png"),
                        100000,
                        LocalDate.now(),
                        LocalDateTime.now().minusDays(7),
                        5);
        List<ChallengeData> data = new ArrayList<>();
        data.add(challengeData);
        ChallengeSlice challengeSlice = new ChallengeSlice(data, true);

        when(getChallengeInfiniteScrollFeedUseCase.execute(any(), anyString(), anyString(), any()))
                .thenReturn(challengeSlice);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/challenge/search")
                                .param("category", category)
                                .param("filter", filter)
                                .param("sortType", sortType))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.challenges", hasSize(1)),
                        jsonPath("$.result.hasNext").value(true));
    }
}
