package depromeet.api.domain.mypage.dto;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.ChallengeCategories;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.userchallenge.domain.Status;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ParticipatedChallenge {

    @Schema(description = "챌린지 ID", example = "13")
    private Long challengeId;

    @Schema(description = "참여중인 챌린지 타이틀", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 대표 이미지", example = "이미지 URL")
    private String imgUrl;

    @Schema(description = "챌린지 진행 여부")
    private Boolean active;

    @Schema(description = "챌린지 기간")
    private Duration duration;

    @Schema(description = "수용 가능 인원")
    private int availableCount;

    @Schema(description = "챌린지 결과 [PROCEEDING, SUCCESS, FAILURE]")
    private Status status;

    //    @Schema(description = "챌린지 기간에 따른 상태 태그 [new, 마감임박]", example = "[\"마감임박\"]")
    //    private List<String> challengeDurationStatus;

    @Schema(description = "챌린지 카테고리  [\"식비\"] ")
    private ChallengeCategories challengeCategories;

    @Schema(description = "챌린지 키워드 [\"#마라탕\", \"#배달\"]")
    private List<String> keywords;

    @Schema(description = "해당 챌린지에 참여 중인 사용자 리스트")
    private List<UserChallenge> userChallenges;

    public static ParticipatedChallenge createParticipatedChallenge(
            Challenge challenge, Status status) {
        return ParticipatedChallenge.builder()
                .challengeId(challenge.getId())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImgUrl())
                .active(challenge.getActive())
                .duration(challenge.getDuration())
                .availableCount(challenge.getAvailableCount())
                .status(status)
                .challengeCategories(challenge.getChallengeCategories())
                .keywords(challenge.getChallengeKeywords().getKeywordNames())
                .userChallenges(challenge.getUserChallenges())
                .build();
    }

    /** 모집 중인 챌린지 태그 계산 active가 false면 계산 active가 true면 [""] */
    //    private static List<String> calculateDurationStatus(Boolean active, Duration duration) {
    //        if (!active) {
    //            return List.of("");
    //        }
    //        List<String> challengeDurationStatus  = new ArrayList<>();
    //
    //
    //    }
    //
    //    public static long calculateDaysSinceStart(LocalDate startAt) {
    //        LocalDate currentDate = LocalDate.now();
    //        return ChronoUnit.DAYS.between(startAt, currentDate);
    //    }
}
