package depromeet.domain.challenge.domain;


import depromeet.domain.category.exception.CategoryNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CategoryType {
    FOOD("식비"),
    HOBBY_LEISURE("취미/여가"),
    FASHION_BEAUTY("패션/뷰티"),
    TRANSPORTATION_AUTOMOBILE("교통/택시");

    private String name;

    public static CategoryType of(String source) {
        return Arrays.stream(CategoryType.values())
                .filter(value -> value.getName().equals(source))
                .findAny()
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }
}
