package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
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

    private HeadCountResponse headCount;

    private List<ProfileResponse> participantsInfo;

    private List<String> rules;

    private boolean isRecruiting;

    private DateInfoResponse dateInfo;

    public static GetChallengeResponse of(Challenge challenge) {
        ProfileResponse profileResponse = new ProfileResponse();
        DateInfoResponse dateInfoResponse = new DateInfoResponse();
        return GetChallengeResponse.builder()
                .challengeId(challenge.getId())
                .categories(challenge.getChallengeCategories().getCategoryNames())
                .title(challenge.getTitle())
                .price(challenge.getPrice())
                .challengeImgUrl(challenge.getImgUrl())
                .keywords(challenge.getChallengeKeywords().getKeywordNames())
                .headCount(
                        new HeadCountResponse(
                                challenge.getAvailableCount(),
                                challenge.getUserChallenges().size()))
                .rules(challenge.getChallengeRuleContents())
                .isRecruiting(challenge.isRecruiting(challenge.getDuration().getStartAt()))
                .dateInfo(dateInfoResponse.toDateInfoResponse(challenge.getDuration()))
                .participantsInfo(profileResponse.toProfileResponses(challenge.getUserChallenges()))
                .build();
    }
}
