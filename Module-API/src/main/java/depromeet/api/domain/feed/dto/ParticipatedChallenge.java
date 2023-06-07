package depromeet.api.domain.feed.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ParticipatedChallenge {

    @Schema(example = "13")
    private Integer challengeRoomId;

    @Schema(example = "배달 10만원 이하로 쓰기")
    private String title;
}
