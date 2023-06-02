package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Category;
import depromeet.domain.rule.domain.Rule;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChallengeResponse {

    private Long id;
    private Category category;
    private String title;
    private int price;
    private String imgUrl;
    private String hashtag;
    private int availableCount;
    private List<Rule> challengeRules;
    private int period;
    private LocalDate startAt;
    private LocalDate endAt;
}
