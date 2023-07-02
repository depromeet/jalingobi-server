package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.JoinChallengeRequest;
import depromeet.api.domain.challenge.dto.response.JoinChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
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
    private final ChallengeMapper challengeMapper;

    public synchronized JoinChallengeResponse execute(
            String socialId, JoinChallengeRequest joinChallengeRequest, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeId);

        challenge.validateInToChallenge(challenge.getDuration().getStartAt());
        challenge.validateCurrentUserCount();
        challenge.validateDuplicatedParticipation(socialId);

        String thumbnailImgUrl = "";
        if (joinChallengeRequest == null) {
            joinChallengeRequest = new JoinChallengeRequest();
            setJoinChallengeRequest(user, joinChallengeRequest);
            thumbnailImgUrl = joinChallengeRequest.getImgUrl();
        } else thumbnailImgUrl = getThumbnailImgUrl(joinChallengeRequest.getImgUrl());

        userChallengeAdaptor.joinChallenge(
                UserChallenge.createUserChallenge(
                        user, challenge, thumbnailImgUrl, joinChallengeRequest.getNickname()));

        return challengeMapper.toJoinChallengeResponse(challenge);
    }

    private String getThumbnailImgUrl(String imgUrl) {
        return imgUrl.replace("original/", "thumb/");
    }

    private void setJoinChallengeRequest(User user, JoinChallengeRequest joinChallengeRequest) {
        joinChallengeRequest.CreateJoinChallengeRequest(
                user.getProfile().getNickname(), user.getProfileImgUrl());
    }
}
