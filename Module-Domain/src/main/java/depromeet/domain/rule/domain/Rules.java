package depromeet.domain.rule.domain;


import depromeet.domain.challenge.domain.Challenge;
import java.util.List;
import java.util.stream.Collectors;

public class Rules {
    private final List<Rule> value;

    public Rules(List<Rule> value) {
        this.value = value;
    }

    public List<Rule> getChallengeRules(Challenge challenge) {
        return value.stream()
                .map(rule -> Rule.createRule(rule.getContent(), challenge))
                .collect(Collectors.toList());
    }
}
