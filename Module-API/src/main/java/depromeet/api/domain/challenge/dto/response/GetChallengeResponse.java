package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.challenge.domain.Challenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetChallengeResponse {

    @Schema(description = "챌린지 ID", example = "1")
    private long challengeId;

    @Schema(description = "챌린지 카테고리", example = "FOOD")
    private String category;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 목표 금액", example = "100000")
    private int price;

    @Schema(description = "챌린지 대표 이미지", example = "test.jpg")
    private String challengeImgUrl;

    @Schema(description = "챌린지 키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    private List<String> keywords;

    private HeadCountResponse headCount;

    private List<ProfileResponse> participantsInfo;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    private List<String> rules;

    @Schema(description = "챌린지 상태", example = "new")
    private String status;

    @Schema(description = "모집 중인지 여부", example = "true")
    private boolean isRecruiting;

    private DateInfoResponse dateInfo;

    public static GetChallengeResponse of(Challenge challenge) {
        ProfileResponse profileResponse = new ProfileResponse();
        DateInfoResponse dateInfoResponse = new DateInfoResponse();
        return GetChallengeResponse.builder()
                .challengeId(challenge.getId())
                .category(challenge.getChallengeCategories().getCategoryNames().get(0))
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
                .status(
                        challenge.checkStatusInChallengeDetail(
                                LocalDate.from(challenge.getCreatedAt())))
                .dateInfo(dateInfoResponse.toDateInfoResponse(challenge.getDuration()))
                .participantsInfo(profileResponse.toProfileResponses(challenge.getUserChallenges()))
                .build();
    }
}
