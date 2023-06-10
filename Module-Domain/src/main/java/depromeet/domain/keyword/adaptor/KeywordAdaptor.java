package depromeet.domain.keyword.adaptor;

import static java.util.stream.Collectors.toList;

import depromeet.common.annotation.Adaptor;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.keyword.repository.KeywordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class KeywordAdaptor {

    private final KeywordRepository keywordRepository;

    public List<Keyword> findOrCreateKeywords(List<String> keywords) {
        return keywords.stream().map(this::getKeywordOrCreateKeywordIfNotExist).collect(toList());
    }

    private Keyword getKeywordOrCreateKeywordIfNotExist(String keywordName) {
        Keyword keyword = new Keyword(keywordName);
        return keywordRepository
                .findByKeyword(keyword.getKeyword())
                .orElseGet(() -> keywordRepository.save(keyword));
    }
}
