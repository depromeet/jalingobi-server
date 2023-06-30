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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JoinChallengeUseCase {

    private final UserAdaptor userAdaptor;
    private final ChallengeAdaptor challengeAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    public synchronized void execute(
            String socialId, JoinChallengeRequest joinChallengeRequest, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeId);

        challenge.validateInToChallenge(challenge.getDuration().getStartAt());
        challenge.validateCurrentUserCount();
        challenge.validateDuplicatedParticipation(socialId);

        // custom user profile만 thumbnail 이미지로 저장
        String thumbnailImgUrl = getThumbnailImgUrl(joinChallengeRequest.getImgUrl());

        userChallengeAdaptor.joinChallenge(
                UserChallenge.createUserChallenge(
                        user, challenge, thumbnailImgUrl, joinChallengeRequest.getNickname()));
    }

    private String getThumbnailImgUrl(String imgUrl) {
        return imgUrl.replace("original/", "thumb/");
    }
}
