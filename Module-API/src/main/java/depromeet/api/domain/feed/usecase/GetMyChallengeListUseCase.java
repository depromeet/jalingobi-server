package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetMyChallengeListResponse;
import depromeet.api.domain.feed.mapper.UserChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class GetMyChallengeListUseCase {

    private final UserChallengeAdaptor userChallengeAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeMapper userChallengeMapper;

    @Transactional(readOnly = true)
    public GetMyChallengeListResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);
        List<UserChallenge> challengeList =
                userChallengeAdaptor.findUserChallengeList(user.getId());
        return userChallengeMapper.toGetMyChallengeListResponse(challengeList);
    }
}
