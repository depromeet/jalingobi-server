package depromeet.domain.challenge.repository;


import depromeet.domain.challenge.domain.UserChallenge;
import java.util.List;

public interface UserChallengeCustomRepository {

    List<UserChallenge> findUserChallengeListById(Long id);
}
