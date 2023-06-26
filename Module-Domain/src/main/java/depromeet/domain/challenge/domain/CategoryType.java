package depromeet.domain.challenge.domain;


import depromeet.domain.category.exception.CategoryNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum CategoryType {
    FOOD,
    HOBBY_LEISURE,
    FASHION_BEAUTY,
    TRANSPORTATION_AUTOMOBILE;

    public static CategoryType of(String source) {
        return Arrays.stream(CategoryType.values())
                .filter(value -> value.toString().equals(source))
                .findAny()
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }
}
