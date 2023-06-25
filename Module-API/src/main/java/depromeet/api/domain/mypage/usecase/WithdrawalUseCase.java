package depromeet.api.domain.mypage.usecase;


import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
public class WithdrawalUseCase {

    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public void execute(String socialId) {
        User user = userAdaptor.findUser(socialId);

        user.withdrawal();
        List<UserChallenge> userChallenges = user.getUserChallenges();
        if (!userChallenges.isEmpty()) {
            userChallenges.forEach(userChallengeAdaptor::quitChallenge);
        }
    }
}
