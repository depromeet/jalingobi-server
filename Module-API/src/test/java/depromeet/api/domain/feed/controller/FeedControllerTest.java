package depromeet.api.domain.feed.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.feed.dto.ParticipatedChallenge;
import depromeet.api.domain.feed.dto.response.GetChallengeProceedingInfoResponse;
import depromeet.api.domain.feed.dto.response.GetMyChallengeListResponse;
import depromeet.api.domain.feed.usecase.GetChallengeFeedUseCase;
import depromeet.api.domain.feed.usecase.GetChallengeProceedingInfoUseCase;
import depromeet.api.domain.feed.usecase.GetMyChallengeListUseCase;
import depromeet.api.domain.feed.usecase.GetMyRoomFeedUseCase;
import depromeet.api.util.AuthenticationUtil;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(
        controllers = FeedController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
class FeedControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private GetMyChallengeListUseCase getMyChallengeListUseCase;
    @MockBean private GetChallengeProceedingInfoUseCase getChallengeProceedingInfoUseCase;
    @MockBean private GetMyRoomFeedUseCase getMyRoomFeedUseCase;
    @MockBean private GetChallengeFeedUseCase getChallengeFeedUseCase;

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
    @DisplayName("참여중인 챌린지 리스트_성공")
    public void getMyChallengeListTest() throws Exception {
        // given
        List<ParticipatedChallenge> participatedChallengeList = new ArrayList<>();
        ParticipatedChallenge response =
                ParticipatedChallenge.builder()
                        .challengeId(12L)
                        .title("마라탕 안먹기")
                        .imgUrl("imgUrl")
                        .active(true)
                        .build();
        participatedChallengeList.add(response);

        GetMyChallengeListResponse output =
                GetMyChallengeListResponse.builder()
                        .participatedChallengeList(participatedChallengeList)
                        .build();

        given(getMyChallengeListUseCase.execute(any())).willReturn(output);

        // when
        ResultActions actions = mockMvc.perform(get("/challenge/my-list"));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.participatedChallengeList[0].challengeId")
                                .value(response.getChallengeId()),
                        jsonPath("$.result.participatedChallengeList[0].title")
                                .value(response.getTitle()),
                        jsonPath("$.result.participatedChallengeList[0].imgUrl")
                                .value(response.getImgUrl()),
                        jsonPath("$.result.participatedChallengeList[0].active")
                                .value(response.getActive()));
    }

    @Test
    @DisplayName("챌린지 진행 정보_성공")
    public void getChallengeProceedingInfoTest() throws Exception {
        // given
        Long challengeId = 23L;

        GetChallengeProceedingInfoResponse response =
                GetChallengeProceedingInfoResponse.builder()
                        .goalCharge(100000)
                        .currentCharge(65000)
                        .percent(65)
                        .dueDay(11L)
                        .build();

        given(getChallengeProceedingInfoUseCase.execute(any(), any())).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(get("/challenge/{challengeId}/proceeding/info", challengeId));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.goalCharge").value(response.getGoalCharge()),
                        jsonPath("$.result.currentCharge").value(response.getCurrentCharge()),
                        jsonPath("$.result.percent").value(response.getPercent()),
                        jsonPath("$.result.dueDay").value(response.getDueDay()));
    }

    @Test
    @DisplayName("내 방 피드_성공")
    public void getMyRoomFeedTest() throws Exception {
        // given

        // when
        ResultActions actions = mockMvc.perform(get("/my-room/feed"));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.goalCharge").value(response.getGoalCharge()),
                        jsonPath("$.result.currentCharge").value(response.getCurrentCharge()),
                        jsonPath("$.result.percent").value(response.getPercent()),
                        jsonPath("$.result.dueDay").value(response.getDueDay()));
    }

    @Test
    @DisplayName("챌린지 피드_성공")
    public void getChallengeFeedTest() throws Exception {
        // given

        // when

        // then
    }
}
