package depromeet.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.api.domain.challenge.usecase.CreateChallengeUseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Category;
import depromeet.domain.rule.adaptor.RuleAdaptor;
import depromeet.domain.rule.domain.Rule;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.Platform;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateChallengeUseCaseTest {

    @Autowired ChallengeMapper challengeMapper;

    @Autowired ChallengeAdaptor challengeAdaptor;

    @Autowired RuleAdaptor ruleAdaptor;

    @Autowired UserAdaptor userAdaptor;

    @Autowired CreateChallengeUseCase challengeUseCase;

    String socialId;

    @BeforeEach
    void setUp() {
        String nickname = "apple";
        String email = "test@test.com";
        socialId = "appleSocialId";
        Platform platform = Platform.APPLE;

        userAdaptor.authUser(nickname, email, socialId, platform);
    }

    @Test
    @DisplayName("챌린지 생성")
    public void execute() {
        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.builder().content("잡담 금지").build());

        CreateChallengeRequest request =
                CreateChallengeRequest.builder()
                        .category(Category.FOOD)
                        .title("마라탕 5만원 이하로 쓰기")
                        .price(50000)
                        .challengeRule(rules)
                        .imageUrl("test.jpg")
                        .hashtag("#마라탕 #5만원챌린지")
                        .availableCount(30)
                        .period(5)
                        .startAt(LocalDate.of(2023, 6, 1))
                        .endAt(LocalDate.of(2023, 6, 5))
                        .build();

        CreateChallengeResponse response = challengeUseCase.execute(request, socialId);

        Assertions.assertAll(
                () -> assertThat(response.getCategory()).isEqualTo(request.getCategory().getName()),
                () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
                () -> assertThat(response.getPrice()).isEqualTo(request.getPrice()),
                () ->
                        assertThat(response.getChallengeRules().size())
                                .isEqualTo(request.getChallengeRule().size()),
                () -> assertThat(response.getImgUrl()).isEqualTo(request.getImageUrl()),
                () -> assertThat(response.getHashtag()).isEqualTo(request.getHashtag()),
                () ->
                        assertThat(response.getAvailableCount())
                                .isEqualTo(request.getAvailableCount()),
                () -> assertThat(response.getPeriod()).isEqualTo(request.getPeriod()),
                () -> assertThat(response.getStartAt()).isEqualTo(request.getStartAt()),
                () -> assertThat(response.getEndAt()).isEqualTo(request.getEndAt()));
    }
}
