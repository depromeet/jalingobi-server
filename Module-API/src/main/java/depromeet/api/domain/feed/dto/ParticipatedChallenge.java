package depromeet.api.domain.feed.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ParticipatedChallenge {

    @Schema(description = "챌린지 방 ID", example = "13")
    private Long challengeRoomId;

    @Schema(description = "참여중인 챌린지 타이틀", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 대표 이미지", example = "이미지 URL")
    private String imgUrl;
}
