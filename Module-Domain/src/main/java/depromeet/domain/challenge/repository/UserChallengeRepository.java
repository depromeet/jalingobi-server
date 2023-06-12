package depromeet.domain.challenge.repository;


import depromeet.domain.challenge.domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository
        extends JpaRepository<UserChallenge, Long>, UserChallengeCustomRepository {}
