package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetChallengeProceedingInfoResponse;
import depromeet.api.domain.feed.mapper.UserChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class GetChallengeProceedingInfoUseCase {

    private final UserChallengeAdaptor userChallengeAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserChallengeMapper userChallengeMapper;

    @Transactional(readOnly = true)
    public GetChallengeProceedingInfoResponse execute(String socialId, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        UserChallenge userChallenge =
                userChallengeAdaptor.findProceedingInfo(user.getId(), challengeId);

        return userChallengeMapper.toGetChallengeProceedingInfoResponse(userChallenge);
    }
}
