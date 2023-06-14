package depromeet.api.domain.mypage.dto.response;


import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserChallengesResponse {
    private List<UserChallenge> userChallenges;

    public static GetUserChallengesResponse of(List<UserChallenge> userChallenges) {
        return GetUserChallengesResponse.builder().userChallenges(userChallenges).build();
    }
}
