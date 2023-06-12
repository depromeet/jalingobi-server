package depromeet.domain.challenge.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.challenge.domain.UserChallenge;
import depromeet.domain.challenge.repository.UserChallengeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adaptor
public class UserChallengeAdaptor {

    private final UserChallengeRepository userChallengeRepository;

    public List<UserChallenge> findUserChallengeList(Long id) {
        return userChallengeRepository.findUserChallengeListById(id);
    }
}
