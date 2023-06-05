package depromeet.domain.record.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.record.domain.QRecord;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final QRecord record = QRecord.record;
}
