package depromeet.domain.challenge.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.challenge.domain.QChallenge;
import depromeet.domain.challenge.domain.QUserChallenge;
import depromeet.domain.challenge.domain.UserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserChallengeCustomRepositoryImpl implements UserChallengeCustomRepository {

    private final JPAQueryFactory queryFactory;

    private QUserChallenge userChallenge = QUserChallenge.userChallenge;
    private QChallenge challenge = QChallenge.challenge;

    @Override
    public List<UserChallenge> findUserChallengeListById(Long userId) {
        return queryFactory
                .selectFrom(userChallenge)
                .join(userChallenge.challenge, challenge)
                .fetchJoin()
                .where(userChallenge.user.id.eq(userId))
                .fetch();
    }
}
