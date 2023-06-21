package depromeet.api.domain.challenge.usecase;


import depromeet.common.annotation.UseCase;
import depromeet.domain.challenge.domain.ChallengeSearchCondition;
import depromeet.domain.challenge.domain.ChallengeSlice;
import depromeet.domain.challenge.repository.ChallengeData;
import depromeet.domain.challenge.repository.ChallengeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetChallengeInfiniteScrollFeedUseCase {

    private final ChallengeQueryRepository challengeQueryRepository;

    public ChallengeSlice execute(
            final String category,
            final String filter,
            final String sortType,
            final Pageable pageable) {
        final ChallengeSearchCondition condition =
                new ChallengeSearchCondition(category, filter, sortType);
        final Slice<ChallengeData> challengeData =
                challengeQueryRepository.searchBy(condition, pageable);
        return new ChallengeSlice(challengeData.getContent(), challengeData.hasNext());
    }
}
