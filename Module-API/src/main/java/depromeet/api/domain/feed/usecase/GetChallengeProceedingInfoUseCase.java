package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.UserChallengeMapper.UserChallengeMapper;
import depromeet.api.domain.feed.dto.response.GetChallengeProceedingInfoResponse;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class GetChallengeProceedingInfoUseCase {

    private final UserChallengeAdaptor userChallengeAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeMapper userChallengeMapper;

    @Transactional(readOnly = true)
    public GetChallengeProceedingInfoResponse execute(String socialId, Long challengeRoomId) {
        User user = userAdaptor.findUser(socialId);
        UserChallenge userChallenge =
                userChallengeAdaptor.findProceedingInfo(user.getId(), challengeRoomId);

        return userChallengeMapper.toGetChallengeProceedingInfoResponse(userChallenge);
    }
}
