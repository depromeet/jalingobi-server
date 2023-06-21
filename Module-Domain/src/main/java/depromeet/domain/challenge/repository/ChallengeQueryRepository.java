package depromeet.domain.challenge.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static depromeet.domain.category.domain.QCategory.category;
import static depromeet.domain.challenge.domain.QChallenge.challenge;
import static depromeet.domain.challenge.domain.QChallengeCategory.challengeCategory;
import static depromeet.domain.challenge.domain.keyword.QChallengeKeyword.challengeKeyword;
import static depromeet.domain.keyword.domain.QKeyword.keyword1;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.challenge.domain.ChallengeSearchCondition;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChallengeQueryRepository {

    private static final String PRICE = "price";

    private final JPAQueryFactory queryFactory;
    private final ConditionFilter conditionFilter = new ConditionFilter();

    public Slice<ChallengeData> searchBy(ChallengeSearchCondition condition, Pageable pageable) {
        final List<ChallengeData> challenges =
                queryFactory
                        .select(makeProjections())
                        .from(challenge)
                        .where(conditionFilter.filterByCondition(condition))
                        .join(challenge.challengeCategories.challengeCategories, challengeCategory)
                        .join(challengeCategory.category, category)
                        .distinct()
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .orderBy(
                                orderByCondition(condition.getSortType())
                                        .toArray(OrderSpecifier[]::new))
                        .fetch();

        setKeywordName(challenges);

        return new SliceImpl<>(
                getCurrentPageChallenges(challenges, pageable),
                pageable,
                hasNext(challenges, pageable));
    }

    private Map<Long, List<String>> findKeywordNameMap(List<Long> challengeIds) {
        return queryFactory
                .from(challenge)
                .leftJoin(challenge.challengeKeywords.challengeKeywords, challengeKeyword)
                .leftJoin(challengeKeyword.keyword, keyword1)
                .where(challenge.id.in(challengeIds))
                .transform(groupBy(challenge.id).as(GroupBy.list(keyword1.keyword)));
    }

    private void setKeywordName(List<ChallengeData> fetch) {
        List<Long> challengeIds = toChallengeIds(fetch);
        Map<Long, List<String>> keywordNameMap = findKeywordNameMap(challengeIds);
        fetch.forEach(o -> o.setKeywordNames(keywordNameMap.get(o.getId())));
    }

    private List<Long> toChallengeIds(List<ChallengeData> result) {
        return result.stream().map(ChallengeData::getId).collect(Collectors.toList());
    }

    private static ConstructorExpression<ChallengeData> makeProjections() {
        return Projections.constructor(
                ChallengeData.class,
                challenge.id,
                challenge.title,
                challenge.userChallenges.size(),
                challenge.availableCount,
                challenge.imgUrl,
                challenge.price,
                challenge.duration.startAt,
                challenge.duration.period);
    }

    private List<OrderSpecifier<?>> orderByCondition(String sortType) {
        List<OrderSpecifier<?>> orderBy = new LinkedList<>();

        if (sortType.equals(PRICE)) {
            orderBy.add(challenge.price.desc());
        } else {
            orderBy.add(challenge.availableCount.desc());
        }

        orderBy.add(challenge.title.asc());

        return orderBy;
    }

    private List<ChallengeData> getCurrentPageChallenges(
            final List<ChallengeData> challenges, final Pageable pageable) {
        if (hasNext(challenges, pageable)) {
            return challenges.subList(0, challenges.size() - 1);
        }
        return challenges;
    }

    private boolean hasNext(final List<ChallengeData> challenges, final Pageable pageable) {
        return challenges.size() > pageable.getPageSize();
    }
}
