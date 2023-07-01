package depromeet.api.domain.mypage.usecase;


import depromeet.api.domain.mypage.exception.CreatorCannotQuitException;
import depromeet.api.domain.mypage.exception.EndChallengeCannotQuitException;
import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class QuitChallengeUseCase {

    private final UserAdaptor userAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;
    private final ChallengeAdaptor challengeAdaptor;

    @Transactional
    public void execute(String socialId, Long challengeId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(challengeId);

        // 종료된 챌린지는 나가기 불가
        if (challenge.isEnd()) throw EndChallengeCannotQuitException.EXCEPTION;

        // 챌린지 생성자는 나가기 불가
        if (user.getId() == challenge.getCreatedBy().getId())
            throw CreatorCannotQuitException.EXCEPTION;

        UserChallenge userChallenge =
                userChallengeAdaptor.findUserChallenge(challengeId, user.getId());

        if (challenge.isProceeding()) user.minusScore();

        userChallengeAdaptor.quitChallenge(userChallenge);
    }
}
