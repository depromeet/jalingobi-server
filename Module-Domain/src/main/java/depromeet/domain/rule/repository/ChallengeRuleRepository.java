package depromeet.domain.rule.repository;


import depromeet.domain.rule.domain.ChallengeRule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRuleRepository extends JpaRepository<ChallengeRule, Long> {
    List<ChallengeRule> findRulesByChallengeId(Long challengeId);
}
