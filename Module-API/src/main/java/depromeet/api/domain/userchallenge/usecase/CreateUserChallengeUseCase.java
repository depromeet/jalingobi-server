package depromeet.api.domain.userchallenge.usecase;


import depromeet.api.domain.userchallenge.dto.request.CreateUserChallengeRequest;
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
public class CreateUserChallengeUseCase {

    private final UserAdaptor userAdaptor;
    private final ChallengeAdaptor challengeAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public void execute(String socialId, CreateUserChallengeRequest userChallengeRequest) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(userChallengeRequest.getChallengeId());

        challenge.validateInToChallenge(challenge.getDuration().getStartAt());
        challenge.validateCurrentUserCount();
        challenge.validateDuplicatedParticipation(socialId);

        userChallengeAdaptor.joinChallenge(
                UserChallenge.createUserChallenge(
                        user,
                        challenge,
                        userChallengeRequest.getNickname(),
                        userChallengeRequest.getCurrentCharge()));
    }
}
