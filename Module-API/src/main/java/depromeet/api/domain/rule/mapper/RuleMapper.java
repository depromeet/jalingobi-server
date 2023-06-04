package depromeet.api.domain.rule.mapper;


import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.rule.domain.Rule;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class RuleMapper {

    public Rule toEntity(String content, Challenge challenge) {
        return Rule.createRule(content, challenge);
    }
}
