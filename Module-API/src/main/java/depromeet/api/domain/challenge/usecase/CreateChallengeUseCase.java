package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.rule.adaptor.RuleAdaptor;
import depromeet.domain.rule.domain.Rule;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateChallengeUseCase {

    private final ChallengeMapper challengeMapper;
    private final ChallengeAdaptor challengeAdaptor;
    private final RuleAdaptor ruleAdaptor;
    private final UserAdaptor userAdaptor;

    @Transactional
    public CreateChallengeResponse execute(CreateChallengeRequest request, String socialId) {
        User currentUser = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.save(challengeMapper.toEntity(request, currentUser));
        List<Rule> rules = ruleAdaptor.findByChallengeId(challenge.getId());
        return challengeMapper.toCreateChallengeResponse(challenge, rules);
    }
}
