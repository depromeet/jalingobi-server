package depromeet.api.domain.keyword.dto;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class KeywordsDto {

    private List<String> keywordNames;

    private KeywordsDto() {}

    public KeywordsDto(List<String> keywordNames) {
        this.keywordNames = keywordNames;
    }

    public List<String> getKeywordNames() {
        return Optional.ofNullable(keywordNames).orElseGet(Collections::emptyList);
    }
}
