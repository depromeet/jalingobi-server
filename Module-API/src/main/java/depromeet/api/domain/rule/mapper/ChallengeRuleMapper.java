package depromeet.api.domain.rule.mapper;


import depromeet.common.annotation.Mapper;
import depromeet.domain.rule.domain.ChallengeRule;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeRuleMapper {

    public List<ChallengeRule> toEntities(List<String> content) {
        return ChallengeRule.createRule(content);
    }
}
