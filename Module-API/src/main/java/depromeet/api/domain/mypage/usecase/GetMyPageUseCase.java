package depromeet.api.domain.mypage.usecase;


import depromeet.api.domain.mypage.dto.response.GetMyPageResponse;
import depromeet.api.domain.mypage.mapper.MyPageMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.Status;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMyPageUseCase {
    private final MyPageMapper myPageMapper;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public GetMyPageResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);
        Social social = user.getSocial();
        Profile profile = user.getProfile();

        Boolean notification = user.getNotification();

        List<UserChallenge> challengeList =
                userChallengeAdaptor.findUserChallengeList(user.getId());
        Map<Status, Long> userChallengeResult = checkUserChallengeStatusCount(challengeList);

        return myPageMapper.toGetMyPageResponse(social, profile, notification, userChallengeResult);
    }

    private Map<Status, Long> checkUserChallengeStatusCount(List<UserChallenge> challengeList) {
        Map<Status, Long> userChallengeResult = new HashMap<>();

        // 진행 중은 대기 + 진행
        long waitingCount = countUserChallengeByStatus(challengeList, Status.WAITING);
        long proceedingCount = countUserChallengeByStatus(challengeList, Status.PROCEEDING);
        userChallengeResult.put(Status.PROCEEDING, waitingCount + proceedingCount);
        long successCount = countUserChallengeByStatus(challengeList, Status.SUCCESS);
        userChallengeResult.put(Status.SUCCESS, successCount);
        // 완료는 성공 + 실패
        long failureCount = countUserChallengeByStatus(challengeList, Status.FAILURE);
        userChallengeResult.put(Status.COMPLETED, successCount + failureCount);

        return userChallengeResult;
    }

    private long countUserChallengeByStatus(List<UserChallenge> challengeList, Status status) {
        return challengeList.stream()
                .filter(userChallenge -> userChallenge.getStatus() == status)
                .count();
    }
}
