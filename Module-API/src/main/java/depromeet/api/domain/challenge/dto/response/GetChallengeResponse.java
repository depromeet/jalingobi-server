package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetChallengeResponse {

    private long challengeId;

    private List<String> categories;

    private String title;

    private int price;

    private String challengeImgUrl;

    private List<String> keywords;

    private int availableCount;

    private int participants;

    private List<ProfileResponse> participantsInfo;

    private List<String> rules;

    private boolean isRecruiting;

    private int period;

    private LocalDate startAt;

    private LocalDate endAt;

    public static GetChallengeResponse of(Challenge challenge) {
        ProfileResponse profileResponse = new ProfileResponse();
        return GetChallengeResponse.builder()
                .challengeId(challenge.getId())
                .categories(challenge.getChallengeCategories().getCategoryNames())
                .title(challenge.getTitle())
                .price(challenge.getPrice())
                .challengeImgUrl(challenge.getImgUrl())
                .keywords(challenge.getChallengeKeywords().getKeywordNames())
                .availableCount(challenge.getAvailableCount())
                .participants(challenge.getUserChallenges().size())
                .rules(challenge.getChallengeRuleContents())
                .isRecruiting(challenge.isRecruiting(challenge.getDuration().getStartAt()))
                .startAt(challenge.getDuration().getStartAt())
                .endAt(challenge.getDuration().getEndAt())
                .period(challenge.getPrice())
                .participantsInfo(profileResponse.toProfileResponses(challenge.getUserChallenges()))
                .build();
    }
}
