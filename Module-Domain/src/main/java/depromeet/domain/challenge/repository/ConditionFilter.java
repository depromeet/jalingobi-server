package depromeet.domain.challenge.repository;

import static depromeet.domain.challenge.domain.QChallenge.challenge;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import depromeet.domain.challenge.domain.CategoryType;
import depromeet.domain.challenge.domain.ChallengeSearchCondition;
import java.time.LocalDate;

public class ConditionFilter {

    private static final String RECRUIT = "recruit";
    private static final String ALL = "all";

    public BooleanBuilder filterByCondition(ChallengeSearchCondition condition) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        filterByCategory(booleanBuilder, condition.getCategory());
        isRecruiting(booleanBuilder, condition.getFilter());
        allExcludeFinished(booleanBuilder, condition.getFilter()); // 모집 중 + 진행 중

        return booleanBuilder;
    }

    private void filterByCategory(BooleanBuilder booleanBuilder, String category) {
        if (!category.isBlank()) {
            CategoryType categoryType = CategoryType.of(category);
            booleanBuilder.and(
                    challenge
                            .challengeCategories
                            .challengeCategories
                            .any()
                            .category
                            .name
                            .eq(String.valueOf(categoryType)));
        }
    }

    private void isRecruiting(BooleanBuilder booleanBuilder, String filter) {
        if (filter.equals(RECRUIT)) {
            booleanBuilder.and(beforeStarted());
        }
    }

    private void allExcludeFinished(BooleanBuilder booleanBuilder, String filter) {
        if (filter.equals(ALL)) {
            booleanBuilder.and(beforeEnd());
        }
    }

    private BooleanExpression beforeStarted() {
        return challenge.duration.startAt.lt(LocalDate.now());
    }

    private BooleanExpression beforeEnd() {
        return challenge.duration.endAt.goe(LocalDate.now());
    }
}
