package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.MyFeed;
import depromeet.api.domain.feed.dto.ParticipatedChallenge;
import java.util.List;
import lombok.Data;

@Data
public class GetMyRoomResponse {

    private List<ParticipatedChallenge> participatedChallengeList;

    private List<MyFeed> myFeedList;
}
