package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.ParticipatedChallenge;
import java.util.List;
import lombok.Data;

@Data
public class GetMyChallengeListResponse {
    private List<ParticipatedChallenge> participatedChallengeList;
}
