package depromeet.domain.record.adaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.ChallengeCategories;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.challenge.domain.keyword.ChallengeKeywords;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.record.domain.Evaluation;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.repository.RecordRepository;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.user.domain.User;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RecordAdaptorTest {
    // mock 객체 생성
    @Mock private RecordRepository recordRepository;

    private RecordAdaptor recordAdaptor;

    @BeforeEach
    public void setup() {
        // mock 객체 초기화
        MockitoAnnotations.openMocks(this);
        recordAdaptor = new RecordAdaptor(recordRepository);
    }

    @Test
    @DisplayName("[Record Adaptor] 챌린지 기록 저장 메서드 테스트")
    public void RecordAdaptorTest() throws Exception {
        // given
        // 어우 다른 메서드에 수정이 해당 테스트에 영향 가지 않도록 직접 생성하니깐 몹시 귀찮네요.. 생성 메서드 쓰고 싶당
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";
        Profile profile = Profile.createProfile(nickname, email, null);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();
        ChallengeKeywords challengeKeywords = new ChallengeKeywords();
        challengeKeywords.add(mock(Challenge.class), mock(Keyword.class));
        ChallengeCategories challengeCategories = new ChallengeCategories();
        challengeCategories.add(mock(Challenge.class), mock(Category.class));

        Challenge challenge =
                Challenge.builder()
                        .challengeCategories(challengeCategories)
                        .title("식비 줄이기 챌린지")
                        .price(5000)
                        .imgUrl("")
                        .challengeKeywords(challengeKeywords)
                        .availableCount(5)
                        .createdBy("정유니")
                        .duration(
                                Duration.builder()
                                        .period(10)
                                        .startAt(LocalDate.of(2023, 6, 1))
                                        .endAt(LocalDate.of(2023, 6, 11))
                                        .build())
                        .build();

        Record record =
                Record.createRecord(challenge, user, 4000, "커피", "커피는 무죄", "", Evaluation.ONE);

        given(recordRepository.save(record)).willReturn(record);

        // when
        Record savedRecord = recordAdaptor.save(record);

        // then
        verify(recordRepository, times(1)).save(record);
        Assertions.assertThat(savedRecord.getTitle()).isEqualTo(record.getTitle());
        assertEquals(record, savedRecord);
    }
}
