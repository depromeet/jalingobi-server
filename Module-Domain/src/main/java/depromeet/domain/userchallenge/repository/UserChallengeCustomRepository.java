package depromeet.domain.userchallenge.repository;


import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import java.util.Optional;

public interface UserChallengeCustomRepository {

    List<UserChallenge> findUserChallengeListById(Long id);

    Optional<UserChallenge> findUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);
}
