package depromeet.api.domain.mypage.usecase;


import depromeet.api.domain.mypage.dto.response.GetUserChallengesResponse;
import depromeet.api.domain.mypage.mapper.MyPageMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUserChallengesUseCase {
    private final MyPageMapper myPageMapper;
    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public GetUserChallengesResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);
        List<UserChallenge> userChallenges =
                userChallengeAdaptor.findUserChallengeList(user.getId());

        return myPageMapper.toGetUserChallengesResponse(userChallenges);
    }
}
