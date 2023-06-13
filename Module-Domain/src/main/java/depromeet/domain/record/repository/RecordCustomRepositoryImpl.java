package depromeet.domain.record.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.challenge.domain.QChallenge;
import depromeet.domain.record.domain.QRecord;
import depromeet.domain.record.domain.Record;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final QRecord record = QRecord.record;
    private final QChallenge challenge = QChallenge.challenge;

    @Override
    public List<Record> findMyRecordList(Long userId, Integer offset, Integer limit) {
        return queryFactory
                .selectFrom(record)
                .where(record.user.id.eq(userId))
                .join(record.challenge, challenge)
                .fetchJoin()
                .orderBy(record.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer countMyRecordList(Long userId) {
        return queryFactory.selectFrom(record).where(record.user.id.eq(userId)).fetch().size();
    }
}
