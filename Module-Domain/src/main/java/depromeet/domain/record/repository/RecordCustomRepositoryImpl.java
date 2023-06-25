package depromeet.domain.record.repository;


import com.querydsl.core.BooleanBuilder;
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
                .where(isEqualUser(userId))
                .orderBy(record.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer countMyRecordList(Long userId) {
        return queryFactory.selectFrom(record).where(isEqualUser(userId)).fetch().size();
    }

    @Override
    public List<Record> findChallengeRecordList(Long challengeId, Long recordId, Integer limit) {
        return queryFactory
                .selectFrom(record)
                .join(record.challenge, challenge)
                .fetchJoin()
                .join(record.userChallenge, userChallenge)
                .fetchJoin()
                .where(isEqualChallenge(challengeId).and(offsetRecordId(recordId)))
                .orderBy(record.createdAt.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Record> findFirstChallengeRecordList(Long challengeId, Integer limit) {
        return queryFactory
                .selectFrom(record)
                .join(record.challenge, challenge)
                .fetchJoin()
                .join(record.userChallenge, userChallenge)
                .fetchJoin()
                .where(isEqualChallenge(challengeId))
                .orderBy(record.createdAt.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer countChallengeRecordList(Long challengeId) {
        return queryFactory.selectFrom(record).where(isEqualChallenge(challengeId)).fetch().size();
    }

    private BooleanBuilder offsetRecordId(Long recordId) {
        if (recordId != null) return new BooleanBuilder(record.id.lt(recordId));
        else return new BooleanBuilder();
    }

    private BooleanBuilder isEqualChallenge(Long challengeId) {
        if (challengeId != null) return new BooleanBuilder(record.id.lt(challengeId));
        else return new BooleanBuilder();
    }

    private BooleanBuilder isEqualUser(Long userId) {
        if (userId != null) return new BooleanBuilder(record.id.lt(userId));
        else return new BooleanBuilder();
    }
}
