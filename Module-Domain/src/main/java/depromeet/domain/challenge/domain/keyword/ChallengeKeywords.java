package depromeet.domain.challenge.domain.keyword;

import static java.util.stream.Collectors.toList;

import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.keyword.exception.AllKeywordLengthIsOverException;
import depromeet.domain.keyword.exception.CannotAddKeywordException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class ChallengeKeywords {

    private static final int MAX_KEYWORD_LENGTH = 28;

    @OneToMany(
            mappedBy = "challenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<ChallengeKeyword> challengeKeywords;

    public ChallengeKeywords() {
        this(new ArrayList<>());
    }

    public ChallengeKeywords(List<ChallengeKeyword> challengeKeywords) {
        this.challengeKeywords = challengeKeywords;
    }

    public void add(Challenge challenge, Keyword keyword) {
        addAll(challenge, List.of(keyword));
    }

    public void addAll(Challenge challenge, List<Keyword> keywords) {
        validateLengthKeyword(keywords);
        validateDuplicateKeyword(keywords);
        keywords.forEach(this::validateDuplicateKeywordAlreadyExistsInChallenge);

        keywords.stream()
                .map(
                        keyword ->
                                ChallengeKeyword.builder()
                                        .challenge(challenge)
                                        .keyword(keyword)
                                        .build())
                .forEach(challengeKeywords::add);
    }

    private void validateDuplicateKeyword(List<Keyword> keywords) {
        long distinctCountOfNewKeywords =
                keywords.stream().map(Keyword::getKeyword).distinct().count();

        if (distinctCountOfNewKeywords != keywords.size()) {
            throw CannotAddKeywordException.EXCEPTION;
        }
    }

    private void validateDuplicateKeywordAlreadyExistsInChallenge(Keyword keyword) {
        boolean isDuplicate =
                challengeKeywords.stream()
                        .anyMatch(challengeKeyword -> challengeKeyword.hasSameKeyword(keyword));

        if (isDuplicate) {
            throw CannotAddKeywordException.EXCEPTION;
        }
    }

    public void validateLengthKeyword(List<Keyword> keywords) {
        if (keywords.stream().map(Keyword::getKeyword).collect(Collectors.joining(" ")).length()
                > MAX_KEYWORD_LENGTH) {
            throw AllKeywordLengthIsOverException.EXCEPTION;
        }
    }

    public void clear() {
        challengeKeywords.clear();
    }

    public List<Keyword> getKeywords() {
        return challengeKeywords.stream().map(ChallengeKeyword::getKeyword).collect(toList());
    }

    public List<String> getKeywordNames() {
        return getKeywords().stream().map(Keyword::getKeyword).collect(toList());
    }
}
