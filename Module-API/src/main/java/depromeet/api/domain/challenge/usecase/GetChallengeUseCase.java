package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.response.GetChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetChallengeUseCase {

    private final ChallengeAdaptor challengeAdaptor;
    private final ChallengeMapper challengeMapper;

    public GetChallengeResponse execute(long challengeId) {
        Challenge savedChallenge = challengeAdaptor.findChallenge(challengeId);
        return challengeMapper.toGetChallengeResponse(savedChallenge);
    }
}
