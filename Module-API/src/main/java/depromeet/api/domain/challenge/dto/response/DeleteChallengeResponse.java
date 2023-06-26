package depromeet.api.domain.challenge.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteChallengeResponse {

    @Schema(description = "챌린지 ID", example = "1")
    private long challengeId;
}
