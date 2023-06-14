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

    @Schema(description = "챌린지 진행 여부")
    private Boolean active;

    public static ParticipatedChallenge createParticipatedChallenge(Challenge challenge) {
        return ParticipatedChallenge.builder()
                .challengeId(challenge.getId())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImgUrl())
                .active(challenge.getActive())
                .build();
    }
}
