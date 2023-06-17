package depromeet.domain.userchallenge.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.UserChallenge;
import depromeet.domain.userchallenge.exception.ProgressInfoNotFoundException;
import depromeet.domain.userchallenge.exception.UserNotParticipatedInChallengeException;
import depromeet.domain.userchallenge.repository.UserChallengeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserChallengeAdaptor {

    private final UserChallengeRepository userChallengeRepository;

    public UserChallenge findUserChallenge(Challenge challenge, User user) {
        return userChallengeRepository
                .findByChallengeAndUser(challenge, user)
                .orElseThrow(() -> UserNotParticipatedInChallengeException.EXCEPTION);
    }

    public UserChallenge findUserChallenge(Long challengeId, Long userId) {
        return userChallengeRepository
                .findByChallengeIdAndUserId(challengeId, userId)
                .orElseThrow(() -> UserNotParticipatedInChallengeException.EXCEPTION);
    }

    public List<UserChallenge> findUserChallengeList(Long id) {
        return userChallengeRepository.findUserChallengeListById(id);
    }

    public UserChallenge findProceedingInfo(Long userId, Long challengeId) {
        return userChallengeRepository
                .findUserChallengeByUserIdAndChallengeId(userId, challengeId)
                .orElseThrow(() -> ProgressInfoNotFoundException.EXCEPTION);
    }

    public UserChallenge validateParticipatedInChallenge(Long userId, Long challengeId) {
        return userChallengeRepository
                .findByUserIdAndchallengeId(userId, challengeId)
                .orElseThrow(() -> UserNotParticipatedInChallengeException.EXCEPTION);
    }

    public void joinChallenge(UserChallenge userChallenge) {
        userChallengeRepository.save(userChallenge);
    }

    public void quitChallenge(UserChallenge userChallenge) {
        userChallengeRepository.delete(userChallenge);
    }
}
