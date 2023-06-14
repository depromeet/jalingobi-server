package depromeet.domain.userchallenge.repository;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserChallengeRepository
        extends JpaRepository<UserChallenge, Long>, UserChallengeCustomRepository {

    @Query(
            value =
                    "SELECT uc FROM UserChallenge uc WHERE uc.user.id = :uid AND uc.challenge.id = :cid")
    Optional<UserChallenge> findByUserIdAndchallengeId(
            @Param("uid") Long userId, @Param("cid") Long challengeId);

    Optional<UserChallenge> findByChallengeAndUser(Challenge challenge, User user);
}
