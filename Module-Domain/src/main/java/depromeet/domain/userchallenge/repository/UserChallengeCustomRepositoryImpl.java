package depromeet.domain.userchallenge.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.challenge.domain.QChallenge;
import depromeet.domain.userchallenge.domain.QUserChallenge;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<UserChallenge> findUserChallengeByUserIdAndChallengeRoomId(
            Long userId, Long challengeRoomId) {
        return queryFactory
                .selectFrom(userChallenge)
                .join(userChallenge.challenge, challenge)
                .fetchJoin()
                .where(
                        userChallenge
                                .user
                                .id
                                .eq(userId)
                                .and(userChallenge.challenge.id.eq(challengeRoomId)))
                .stream()
                .findAny();
    }
}
