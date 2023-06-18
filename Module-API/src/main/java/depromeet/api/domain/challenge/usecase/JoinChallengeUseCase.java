package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.JoinChallengeRequest;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class JoinChallengeUseCase {

    private final UserAdaptor userAdaptor;
    private final ChallengeAdaptor challengeAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public void execute(
            String socialId, JoinChallengeRequest joinChallengeRequest, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeId);

        challenge.validateInToChallenge(challenge.getDuration().getStartAt());
        challenge.validateCurrentUserCount();
        challenge.validateDuplicatedParticipation(socialId);

        userChallengeAdaptor.joinChallenge(
                UserChallenge.createUserChallenge(
                        user,
                        challenge,
                        joinChallengeRequest.getImgUrl(),
                        joinChallengeRequest.getNickname(),
                        joinChallengeRequest.getCurrentCharge()));
    }
}
