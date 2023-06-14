package depromeet.domain.record.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.challenge.domain.QChallenge;
import depromeet.domain.record.domain.QRecord;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.QUserChallenge;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final QRecord record = QRecord.record;
    private final QChallenge challenge = QChallenge.challenge;
    private final QUserChallenge userChallenge = QUserChallenge.userChallenge;

    @Override
    public List<Record> findMyRecordList(Long userId, Integer offset, Integer limit) {
        return queryFactory
                .selectFrom(record)
                .join(record.challenge, challenge)
                .fetchJoin()
                .where(record.user.id.eq(userId))
                .orderBy(record.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer countMyRecordList(Long userId) {
        return queryFactory.selectFrom(record).where(record.user.id.eq(userId)).fetch().size();
    }

    @Override
    public List<Record> findChallengeRecordList(
            Long challengeRoomId, Long recordId, Integer limit) {
        return queryFactory
                .selectFrom(record)
                .join(record.challenge, challenge)
                .fetchJoin()
                .join(record.userChallenge, userChallenge)
                .fetchJoin()
                .where(record.challenge.id.eq(challengeRoomId).and(record.id.gt(recordId)))
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer countChallengeRecordList(Long challengeRoomId) {
        return queryFactory
                .selectFrom(record)
                .where(record.challenge.id.eq(challengeRoomId))
                .fetch()
                .size();
    }
}
