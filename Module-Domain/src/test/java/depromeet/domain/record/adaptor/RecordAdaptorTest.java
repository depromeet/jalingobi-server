package depromeet.domain.record.adaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import depromeet.domain.challenge.domain.Category;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.record.domain.Record;
import depromeet.domain.record.domain.RecordEvaluation;
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

        Challenge challenge =
                Challenge.builder()
                        .category(Category.FOOD)
                        .title("식비 줄이기 챌린지")
                        .price(5000)
                        .imgUrl("")
                        .hashtag("#food")
                        .availableCount(5)
                        .user(user)
                        .duration(
                                Duration.builder()
                                        .period(10)
                                        .startAt(LocalDate.of(2023, 6, 1))
                                        .endAt(LocalDate.of(2023, 6, 11))
                                        .build())
                        .build();

        Record record =
                Record.createRecord(
                        challenge, user, 4000, "커피", "커피는 무죄", "", RecordEvaluation.ONE);

        // when
        when(recordRepository.save(record)).thenReturn(record);
        Record savedRecord = recordAdaptor.save(record);

        // then
        // 예상한 결과와 실제 결과가 일치하는지 확인
        verify(recordRepository, times(1)).save(record);
        Assertions.assertThat(savedRecord.getName()).isEqualTo(record.getName());
        assertEquals(record, savedRecord);
    }
}