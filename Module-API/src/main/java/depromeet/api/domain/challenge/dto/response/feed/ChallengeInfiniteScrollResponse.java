package depromeet.api.domain.challenge.dto.response.feed;


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

    private List<ChallengeFeedResponse> data;
    private Boolean hasNext;
}
