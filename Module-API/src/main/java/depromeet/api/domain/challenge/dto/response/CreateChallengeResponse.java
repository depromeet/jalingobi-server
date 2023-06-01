package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Category;
import depromeet.domain.rule.domain.Rule;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChallengeResponse {

    private Long id;
    private Category category;
    private String title;
    private String price;
    private String imgUrl;
    private String hashtag;
    private int availableCount;
    private List<Rule> challengeRules;
    private int period;
    private Date startAt;
    private Date endAt;
}
