package depromeet.api.domain.feed.UserChallengeMapper;


import depromeet.api.domain.feed.dto.response.GetChallengeProceedingInfoResponse;
import depromeet.api.domain.feed.dto.response.GetMyChallengeListResponse;
import depromeet.domain.challenge.domain.UserChallenge;
import java.util.List;

public class UserChallengeMapper {

    public GetMyChallengeListResponse toGetMyChallengeListResponse(
            List<UserChallenge> userChallengeList) {
        return GetMyChallengeListResponse.of(userChallengeList);
    }

    public GetChallengeProceedingInfoResponse toGetChallengeProceedingInfoResponse(
            UserChallenge userChallenge) {
        return GetChallengeProceedingInfoResponse.of(userChallenge);
    }
}
