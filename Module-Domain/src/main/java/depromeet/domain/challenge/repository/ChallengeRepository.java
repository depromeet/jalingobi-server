package depromeet.domain.challenge.repository;


import depromeet.domain.challenge.domain.Challenge;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query(
            value =
                    "SELECT c FROM Challenge c WHERE c.duration.startAt = :now AND c.status = :status")
    List<Challenge> findStartChallenge(LocalDate now, String status);

    @Query(
            value =
                    "SELECT c FROM Challenge c WHERE c.duration.endAt = :yesterday AND c.status = :status")
    List<Challenge> findEndChallenge(LocalDate yesterday, String status);
}
