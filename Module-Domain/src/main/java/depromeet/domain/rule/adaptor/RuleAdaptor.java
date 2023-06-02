package depromeet.domain.rule.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.rule.domain.Rule;
import depromeet.domain.rule.repository.RuleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RuleAdaptor {

    private final RuleRepository ruleRepository;

    public List<Rule> findByChallengeId(Long challengeId) {
        return ruleRepository.findRulesByChallengeId(challengeId);
    }
}
