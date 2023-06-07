package depromeet.api.domain.feed.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetChallengeRoomRequest {

    @Schema(example = "5")
    private Integer page;
}
