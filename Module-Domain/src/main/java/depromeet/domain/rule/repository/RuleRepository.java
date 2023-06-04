package depromeet.domain.rule.repository;


import depromeet.domain.rule.domain.Rule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    List<Rule> findRulesByChallengeId(Long challengeId);
}
