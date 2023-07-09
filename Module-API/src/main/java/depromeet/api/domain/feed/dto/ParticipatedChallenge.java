package depromeet.api.domain.feed.dto;


import depromeet.domain.challenge.domain.Challenge;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "챌린지 진행 여부 - RECRUITING, PROCEEDING 둘 중 하나의 값", example = "PROCEEDING")
    private String status;

    @Schema(description = "최대 참여 인원", example = "20")
    private Integer maxParticipants;

    @Schema(description = "현재 참여 인원", example = "12")
    private Integer participants;

    public static ParticipatedChallenge createParticipatedChallenge(Challenge challenge) {
        Integer participants = challenge.getUserChallenges().size();

        return ParticipatedChallenge.builder()
                .challengeId(challenge.getId())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImage().getThumbUrl())
                .status(challenge.getStatus().toString())
                .maxParticipants(challenge.getAvailableCount())
                .participants(participants)
                .build();
    }
}
