package depromeet.api.domain.challenge.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChallengeResponse {

    @Schema(description = "챌린지 ID", example = "1")
    private Long id;

    public static CreateChallengeResponse of(Long challengeId) {
        return CreateChallengeResponse.builder().id(challengeId).build();
    }
}
