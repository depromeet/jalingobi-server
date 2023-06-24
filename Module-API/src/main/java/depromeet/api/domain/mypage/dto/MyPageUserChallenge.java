package depromeet.api.domain.mypage.dto;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.userchallenge.domain.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MyPageUserChallenge {

    @Schema(description = "챌린지 ID", example = "1")
    private Long challengeId;

    @Schema(description = "참여중인 챌린지 타이틀", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 대표 이미지", example = "이미지 URL")
    private String imgUrl;

    @Schema(description = "챌린지 진행 여부")
    private Boolean active;

    @Schema(description = "챌린지 기간")
    private DateInfo duration;

    @Schema(description = "수용 가능 인원")
    private Integer availableCount;

    @Schema(description = "해당 챌린지에 참여 중인 사용자 수")
    private Integer participantCount;

    @Schema(description = "챌린지 결과")
    private String status;

    @Schema(description = "챌린지 기간에 따른 상태 태그 [new, 마감임박, 오픈 예정, 해당없음]", example = "[\"마감임박\"]")
    private String statusTag;

    @Schema(description = "챌린지 카테고리  [\"식비\"] ")
    private List<String> categories;

    @Schema(description = "챌린지 키워드 [\"#마라탕\", \"#배달\"]")
    private List<String> keywords;

    public static MyPageUserChallenge createParticipatedChallenge(
            Challenge challenge, Status status) {

        DateInfo dateInfo = new DateInfo(challenge.getDuration());

        return MyPageUserChallenge.builder()
                .challengeId(challenge.getId())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImgUrl())
                .active(challenge.isProceeding())
                .duration(dateInfo)
                .availableCount(challenge.getAvailableCount())
                .status(status.getValue())
                .statusTag(
                        challenge.checkStatusInChallengeDetail(
                                LocalDate.from(challenge.getCreatedAt())))
                .categories(challenge.getChallengeCategories().getCategoryNames())
                .keywords(challenge.getChallengeKeywords().getKeywordNames())
                .participantCount(challenge.getUserChallenges().size())
                .build();
    }
}
