package depromeet.api.domain.mypage.dto.response;


import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.userchallenge.domain.Status;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMyPageResponse {
    private Social social;
    private Profile profile;
    private Boolean notification;
    private Map<String, Long> userChallengeResult;

    public static GetMyPageResponse of(
            Social social,
            Profile profile,
            Boolean notification,
            Map<Status, Long> challengeResult) {

        Map<String, Long> result =
                challengeResult.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        entry -> entry.getKey().getValue(), Map.Entry::getValue));

        return GetMyPageResponse.builder()
                .social(social)
                .profile(profile)
                .notification(notification)
                .userChallengeResult(result)
                .build();
    }
}
