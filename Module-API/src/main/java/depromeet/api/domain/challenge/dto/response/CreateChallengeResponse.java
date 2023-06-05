package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.rule.domain.ChallengeRule;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChallengeResponse {

    private Long id;
    private String category;
    private String title;
    private int price;
    private String imgUrl;
    private String hashtag;
    private int availableCount;
    private List<ChallengeRule> challengeRules;
    private int period;
    private LocalDate startAt;
    private LocalDate endAt;

    public static CreateChallengeResponse of(Challenge challenge) {
        return CreateChallengeResponse.builder()
                .id(challenge.getId())
                .category(challenge.getCategory().getName())
                .title(challenge.getTitle())
                .price(challenge.getPrice())
                .imgUrl(challenge.getImgUrl())
                .hashtag(challenge.getHashtag())
                .availableCount(challenge.getAvailableCount())
                .challengeRules(challenge.getChallengeRules())
                .period(challenge.getDuration().getPeriod())
                .startAt(challenge.getDuration().getStartAt())
                .endAt(challenge.getDuration().getEndAt())
                .build();
    }
}
