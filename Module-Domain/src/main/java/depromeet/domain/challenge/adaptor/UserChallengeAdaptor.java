package depromeet.domain.challenge.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.challenge.exception.ProgressInfoNotFoundException;
import depromeet.domain.challenge.exception.UserNotParticipatedInChallengeException;
import depromeet.domain.challenge.repository.UserChallengeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adaptor
public class UserChallengeAdaptor {

    private final UserChallengeRepository userChallengeRepository;

    public List<UserChallenge> findUserChallengeList(Long id) {
        return userChallengeRepository.findUserChallengeListById(id);
    }

    public UserChallenge findProceedingInfo(Long userId, Long challengeRoomId) {
        return userChallengeRepository
                .findUserChallengeByUserIdAndChallengeRoomId(userId, challengeRoomId)
                .orElseThrow(() -> ProgressInfoNotFoundException.EXCEPTION);
    }

    public void validateParticipatedInChallenge(Long userId, Long challengeRoomId) {
        userChallengeRepository
                .findByUserIdAndChallengeRoomId(userId, challengeRoomId)
                .orElseThrow(() -> UserNotParticipatedInChallengeException.EXCEPTION);
    }
}
