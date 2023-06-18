package depromeet.domain.challenge.domain;

import static depromeet.common.exception.CustomExceptionStatus.CANNOT_ADD_CATEGORY;
import static java.util.stream.Collectors.toList;

import depromeet.common.exception.CustomException;
import depromeet.domain.category.domain.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class ChallengeCategories {

    @OneToMany(
            mappedBy = "challenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<ChallengeCategory> challengeCategories;

    public ChallengeCategories() {
        this(new ArrayList<>());
    }

    public ChallengeCategories(List<ChallengeCategory> challengeCategories) {
        this.challengeCategories = challengeCategories;
    }

    public void add(Challenge challenge, depromeet.domain.category.domain.Category category) {
        addAll(challenge, List.of(category));
    }

    public void addAll(
            Challenge challenge, List<depromeet.domain.category.domain.Category> categories) {
        validateDuplicateCategory(categories);
        categories.forEach(this::validateDuplicateCategoryAlreadyExistsInChallenge);

        categories.stream()
                .map(category -> new ChallengeCategory(challenge, category))
                .forEach(challengeCategories::add);
    }

    private void validateDuplicateCategory(
            List<depromeet.domain.category.domain.Category> categories) {
        long distinctCountOfNewTags =
                categories.stream()
                        .map(depromeet.domain.category.domain.Category::getName)
                        .distinct()
                        .count();

        if (distinctCountOfNewTags != categories.size()) {
            throw new CustomException(CANNOT_ADD_CATEGORY);
        }
    }

    private void validateDuplicateCategoryAlreadyExistsInChallenge(Category category) {
        boolean isDuplicate =
                challengeCategories.stream()
                        .anyMatch(challengeCategory -> challengeCategory.hasSameCategory(category));

        if (isDuplicate) {
            throw new CustomException(CANNOT_ADD_CATEGORY);
        }
    }

    public void clear() {
        challengeCategories.clear();
    }

    public List<Category> getCategories() {
        return challengeCategories.stream().map(ChallengeCategory::getCategory).collect(toList());
    }

    public List<String> getCategoryNames() {
        return getCategories().stream().map(Category::getName).collect(toList());
    }
}
