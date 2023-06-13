package depromeet.api.domain.mypage.mapper;


import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.dto.response.GetUserChallengesResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.userchallenge.domain.Status;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class MyPageMapper {
    public GetMyPageResponse toGetMyPageResponse(
            Profile profile, Boolean notification, Map<Status, Integer> userChallengeResult) {
        return GetMyPageResponse.of(profile, notification, userChallengeResult);
    }

    public GetUserChallengesResponse toGetUserChallengesResponse(
            List<UserChallenge> userChallenges) {
        return GetUserChallengesResponse.of(userChallenges);
    }
}