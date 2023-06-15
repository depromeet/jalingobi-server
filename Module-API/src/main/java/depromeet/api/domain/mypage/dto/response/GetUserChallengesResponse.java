package depromeet.api.domain.mypage.dto.response;


import depromeet.api.domain.mypage.dto.MyPageUserChallenge;
import depromeet.domain.config.BaseTime;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetUserChallengesResponse {
    private List<MyPageUserChallenge> participatedChallenges;

    public static GetUserChallengesResponse of(List<UserChallenge> userChallenges) {
        List<MyPageUserChallenge> participatedChallenges =
                userChallenges.stream()
                        .sorted(Comparator.comparing(BaseTime::getCreatedAt))
                        .map(
                                userChallenge ->
                                        MyPageUserChallenge.createParticipatedChallenge(
                                                userChallenge.getChallenge(),
                                                userChallenge.getStatus()))
                        .collect(Collectors.toList());

        return new GetUserChallengesResponse(participatedChallenges);
    }
}
