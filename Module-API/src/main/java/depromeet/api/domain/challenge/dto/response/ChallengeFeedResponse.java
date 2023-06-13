package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeFeedResponse {

    private Long challengeId;

    private Integer curCount;

    private Integer availableCount;

    private String title;

    private String imgUrl;

    private List<String> keywords;

    private LocalDate startAt;

    private Integer period;

    public static ChallengeFeedResponse from(final Challenge challenge) {
        return ChallengeFeedResponse.builder()
                .challengeId(challenge.getId())
                .curCount(challenge.getUserChallenges().size())
                .availableCount(challenge.getAvailableCount())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImgUrl())
                .keywords(challenge.getChallengeKeywords().getKeywordNames())
                .startAt(challenge.getDuration().getStartAt())
                .period(challenge.getDuration().getPeriod())
                .build();
    }
}
