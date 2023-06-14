package depromeet.api.domain.feed.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.feed.dto.*;
import depromeet.api.domain.feed.dto.ChallengeFeed.FeedUserInfo;
import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.dto.response.GetChallengeProceedingInfoResponse;
import depromeet.api.domain.feed.dto.response.GetMyChallengeListResponse;
import depromeet.api.domain.feed.dto.response.GetMyRoomFeedResponse;
import depromeet.api.domain.feed.usecase.GetChallengeFeedUseCase;
import depromeet.api.domain.feed.usecase.GetChallengeProceedingInfoUseCase;
import depromeet.api.domain.feed.usecase.GetMyChallengeListUseCase;
import depromeet.api.domain.feed.usecase.GetMyRoomFeedUseCase;
import depromeet.api.util.AuthenticationUtil;
import java.time.LocalDateTime;
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
        Integer offset = 3;

        FeedRecordInfo feedRecordInfo =
                FeedRecordInfo.builder()
                        .id(27L)
                        .date(LocalDateTime.now())
                        .imgUrl("기록 이미지 url")
                        .content("기록 내용")
                        .price(5000)
                        .build();

        MyFeed.ChallengeInfo challengeInfo =
                MyFeed.ChallengeInfo.builder().imgUrl("챌린지 이미지 url").title("챌린지 타이틀").build();

        EmojiInfo emojiInfo =
                EmojiInfo.builder()
                        .selectedEmoji("미친거지")
                        .crazy(2L)
                        .regretful(0L)
                        .wellDone(3L)
                        .comment(5)
                        .build();

        MyFeed myFeed =
                MyFeed.builder()
                        .recordInfo(feedRecordInfo)
                        .challengeInfo(challengeInfo)
                        .emojiInfo(emojiInfo)
                        .build();

        List<MyFeed> myFeedList = new ArrayList<>();
        myFeedList.add(myFeed);

        GetMyRoomFeedResponse response =
                GetMyRoomFeedResponse.builder()
                        .total(120)
                        .limit(20)
                        .current(15)
                        .myFeedList(myFeedList)
                        .build();

        given(getMyRoomFeedUseCase.execute(any(), any())).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/challenge/my-room/feed").param("offset", String.valueOf(offset)));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.total").value(response.getTotal()),
                        jsonPath("$.result.limit").value(response.getLimit()),
                        jsonPath("$.result.current").value(response.getCurrent()),
                        jsonPath("$.result.myFeedList[0].recordInfo.id")
                                .value(feedRecordInfo.getId()),
                        jsonPath("$.result.myFeedList[0].recordInfo.imgUrl")
                                .value(feedRecordInfo.getImgUrl()),
                        jsonPath("$.result.myFeedList[0].recordInfo.title")
                                .value(feedRecordInfo.getTitle()),
                        jsonPath("$.result.myFeedList[0].recordInfo.content")
                                .value(feedRecordInfo.getContent()),
                        jsonPath("$.result.myFeedList[0].recordInfo.price")
                                .value(feedRecordInfo.getPrice()),
                        jsonPath("$.result.myFeedList[0].challengeInfo.imgUrl")
                                .value(challengeInfo.getImgUrl()),
                        jsonPath("$.result.myFeedList[0].challengeInfo.title")
                                .value(challengeInfo.getTitle()),
                        jsonPath("$.result.myFeedList[0].emojiInfo.selectedEmoji")
                                .value(emojiInfo.getSelectedEmoji()),
                        jsonPath("$.result.myFeedList[0].emojiInfo.crazy")
                                .value(emojiInfo.getCrazy()),
                        jsonPath("$.result.myFeedList[0].emojiInfo.regretful")
                                .value(emojiInfo.getRegretful()),
                        jsonPath("$.result.myFeedList[0].emojiInfo.wellDone")
                                .value(emojiInfo.getWellDone()),
                        jsonPath("$.result.myFeedList[0].emojiInfo.comment")
                                .value(emojiInfo.getComment()));
    }

    @Test
    @DisplayName("챌린지 피드_성공")
    public void getChallengeFeedTest() throws Exception {
        // given
        Long challengeId = 23L;
        Long offsetRecordId = 3L;

        FeedUserInfo userInfo =
                FeedUserInfo.builder()
                        .nickname("tester")
                        .imgUrl("사용자 이미지 url")
                        .currentCharge(34000)
                        .build();

        FeedRecordInfo feedRecordInfo =
                FeedRecordInfo.builder()
                        .id(27L)
                        .date(LocalDateTime.now())
                        .imgUrl("기록 이미지 url")
                        .content("기록 내용")
                        .price(5000)
                        .build();

        EmojiInfo emojiInfo =
                EmojiInfo.builder()
                        .selectedEmoji("미친거지")
                        .crazy(2L)
                        .regretful(0L)
                        .wellDone(3L)
                        .comment(5)
                        .build();

        ChallengeFeed challengeFeed =
                ChallengeFeed.builder()
                        .isMine(true)
                        .userInfo(userInfo)
                        .recordInfo(feedRecordInfo)
                        .emojiInfo(emojiInfo)
                        .build();

        List<ChallengeFeed> challengeFeedList = new ArrayList<>();
        challengeFeedList.add(challengeFeed);

        GetChallengeFeedResponse response =
                GetChallengeFeedResponse.builder()
                        .total(120)
                        .limit(20)
                        .current(15)
                        .lastRecordId(38L)
                        .challengeFeedList(challengeFeedList)
                        .build();

        given(getChallengeFeedUseCase.execute(any(), any(), any())).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/challenge/{challengeId}/feed", challengeId)
                                .param("offsetRecordId", String.valueOf(offsetRecordId)));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.total").value(response.getTotal()),
                        jsonPath("$.result.limit").value(response.getLimit()),
                        jsonPath("$.result.current").value(response.getCurrent()),
                        jsonPath("$.result.lastRecordId").value(response.getLastRecordId()),
                        jsonPath("$.result.challengeFeedList[0].userInfo.nickname")
                                .value(userInfo.getNickname()),
                        jsonPath("$.result.challengeFeedList[0].userInfo.imgUrl")
                                .value(userInfo.getImgUrl()),
                        jsonPath("$.result.challengeFeedList[0].userInfo.currentCharge")
                                .value(userInfo.getCurrentCharge()),
                        jsonPath("$.result.challengeFeedList[0].recordInfo.id")
                                .value(feedRecordInfo.getId()),
                        jsonPath("$.result.challengeFeedList[0].recordInfo.imgUrl")
                                .value(feedRecordInfo.getImgUrl()),
                        jsonPath("$.result.challengeFeedList[0].recordInfo.title")
                                .value(feedRecordInfo.getTitle()),
                        jsonPath("$.result.challengeFeedList[0].recordInfo.content")
                                .value(feedRecordInfo.getContent()),
                        jsonPath("$.result.challengeFeedList[0].recordInfo.price")
                                .value(feedRecordInfo.getPrice()),
                        jsonPath("$.result.challengeFeedList[0].emojiInfo.selectedEmoji")
                                .value(emojiInfo.getSelectedEmoji()),
                        jsonPath("$.result.challengeFeedList[0].emojiInfo.crazy")
                                .value(emojiInfo.getCrazy()),
                        jsonPath("$.result.challengeFeedList[0].emojiInfo.regretful")
                                .value(emojiInfo.getRegretful()),
                        jsonPath("$.result.challengeFeedList[0].emojiInfo.wellDone")
                                .value(emojiInfo.getWellDone()),
                        jsonPath("$.result.challengeFeedList[0].emojiInfo.comment")
                                .value(emojiInfo.getComment()));
    }
}
