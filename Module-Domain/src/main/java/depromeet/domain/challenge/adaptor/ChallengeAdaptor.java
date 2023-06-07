package depromeet.domain.challenge.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.exception.ChallengeNotFoundException;
import depromeet.domain.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ChallengeAdaptor {

    private final ChallengeRepository challengeRepository;

    public Challenge save(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge findChallenge(Long challengeId) {
        return challengeRepository
                .findById(challengeId)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }
}
