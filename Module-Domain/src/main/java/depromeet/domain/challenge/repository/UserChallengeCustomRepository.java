package depromeet.domain.challenge.repository;


import depromeet.domain.challenge.domain.UserChallenge;
import java.util.List;
import java.util.Optional;

public interface UserChallengeCustomRepository {

    List<UserChallenge> findUserChallengeListById(Long id);

    Optional<UserChallenge> findUserChallengeByUserIdAndChallengeRoomId(
            Long userId, Long challengeRoomId);
}
