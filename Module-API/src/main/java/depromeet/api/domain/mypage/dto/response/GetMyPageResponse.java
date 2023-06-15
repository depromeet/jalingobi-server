package depromeet.api.domain.mypage.dto.response;


import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.userchallenge.domain.Status;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMyPageResponse {
    private Social social;
    private Profile profile;
    private Boolean notification;
    private Map<Status, Integer> userChallengeResult;

    public static GetMyPageResponse of(
            Social social,
            Profile profile,
            Boolean notification,
            Map<Status, Integer> userChallengeResult) {
        return GetMyPageResponse.builder()
                .social(social)
                .profile(profile)
                .notification(notification)
                .userChallengeResult(userChallengeResult)
                .build();
    }
}
