package depromeet.domain.challenge.domain;


import depromeet.domain.category.exception.CategoryNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum CategoryType {
    FOOD(DefaultImageProperties.food, ThumbImageProperties.food),
    HOBBY_LEISURE(DefaultImageProperties.hobby, ThumbImageProperties.hobby),
    FASHION_BEAUTY(DefaultImageProperties.fashionBeauty, ThumbImageProperties.fashionBeauty),
    TRANSPORTATION_AUTOMOBILE(
            DefaultImageProperties.transportation, ThumbImageProperties.transportation);

    private final String defaultUrl;
    private final String thumbUrl;

    CategoryType(String defaultUrl, String thumbUrl) {
        this.defaultUrl = defaultUrl;
        this.thumbUrl = thumbUrl;
    }

    public static CategoryType of(String source) {
        return Arrays.stream(CategoryType.values())
                .filter(value -> value.toString().equals(source))
                .findAny()
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }
}
