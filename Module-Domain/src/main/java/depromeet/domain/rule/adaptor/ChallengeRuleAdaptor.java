package depromeet.domain.rule.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.rule.domain.ChallengeRule;
import depromeet.domain.rule.repository.ChallengeRuleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ChallengeRuleAdaptor {

    private final ChallengeRuleRepository ruleRepository;

    public List<ChallengeRule> findByChallengeId(Long challengeId) {
        return ruleRepository.findRulesByChallengeId(challengeId);
    }

    public void saveChallengeRules(List<ChallengeRule> rules) {
        ruleRepository.saveAll(rules);
    }
}
