package depromeet.api.domain.challenge.usecase;


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
public class DeleteChallengeUseCase {

    private final ChallengeAdaptor challengeAdaptor;
    private final UserAdaptor userAdaptor;

    @Transactional
    public void execute(Long challengeId, String socialId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeId);
        challenge.validateDeleteOrUpdate(challenge.getDuration().getStartAt());

        challengeAdaptor.delete(challenge);
    }
}
