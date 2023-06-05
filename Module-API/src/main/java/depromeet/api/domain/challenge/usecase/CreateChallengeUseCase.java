package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateChallengeUseCase {

    private final ChallengeMapper challengeMapper;
    private final ChallengeAdaptor challengeAdaptor;
    private final UserAdaptor userAdaptor;

    @Transactional
    public CreateChallengeResponse execute(CreateChallengeRequest request, String socialId) {
        User currentUser = userAdaptor.findUser(socialId);
        Challenge challenge = challengeMapper.toEntity(request, socialId);
        challenge.addRules(request.getChallengeRule());
        return challengeMapper.toCreateChallengeResponse(challengeAdaptor.save(challenge));
    }
}
