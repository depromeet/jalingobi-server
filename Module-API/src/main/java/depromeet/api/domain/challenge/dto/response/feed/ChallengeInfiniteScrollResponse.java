package depromeet.api.domain.challenge.dto.response.feed;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeInfiniteScrollResponse {

    @Schema(description = "챌린지 피드 데이터")
    private List<ChallengeFeedResponse> data;

    @Schema(description = "그 다음 데이터가 있는지 여부", example = "true")
    private Boolean hasNext;
}
