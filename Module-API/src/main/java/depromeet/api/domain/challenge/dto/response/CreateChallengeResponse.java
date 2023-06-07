package depromeet.api.domain.challenge.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChallengeResponse {

    private Long id;

    public static CreateChallengeResponse of(Long challengeId) {
        return CreateChallengeResponse.builder().id(challengeId).build();
    }
}
