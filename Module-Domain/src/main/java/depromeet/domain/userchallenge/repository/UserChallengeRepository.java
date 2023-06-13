package depromeet.domain.userchallenge.repository;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {

    Optional<UserChallenge> findByChallengeAndUser(Challenge challenge, User user);
}
