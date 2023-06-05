package depromeet.api.domain.rule.mapper;


import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.rule.domain.ChallengeRule;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeRuleMapper {

    public ChallengeRule toEntity(String content, Challenge challenge) {
        return ChallengeRule.createRule(content, challenge);
    }
}
