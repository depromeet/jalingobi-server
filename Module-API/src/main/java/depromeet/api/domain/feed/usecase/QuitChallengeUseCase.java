package depromeet.api.domain.feed.usecase;


import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class QuitChallengeUseCase {

    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public void execute(String socialId, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        UserChallenge userChallenge =
                userChallengeAdaptor.findUserChallenge(challengeId, user.getId());
        userChallengeAdaptor.quitChallenge(userChallenge);
    }
}
