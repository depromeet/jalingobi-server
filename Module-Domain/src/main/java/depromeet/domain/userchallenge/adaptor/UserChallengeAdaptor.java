package depromeet.domain.userchallenge.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.exception.UnparticipatedChallengeUserException;
import depromeet.domain.userchallenge.repository.UserChallengeRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserChallengeAdaptor {

    private final UserChallengeRepository userChallengeRepository;

    public UserChallenge findByUserChallenge(Challenge challenge, User user) {
        return userChallengeRepository
                .findByChallengeAndUser(challenge, user)
                .orElseThrow(() -> UnparticipatedChallengeUserException.EXCEPTION);
    }
}
