package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String imgUrl;

    private String nickname;

    private int level;

    public List<ProfileResponse> toProfileResponses(List<UserChallenge> userChallenges) {
        return userChallenges.stream()
                .map(
                        userChallenge ->
                                new ProfileResponse(
                                        userChallenge.getImgUrl(),
                                        userChallenge.getNickname(),
                                        userChallenge.getUserLevel()))
                .collect(Collectors.toList());
    }
}
