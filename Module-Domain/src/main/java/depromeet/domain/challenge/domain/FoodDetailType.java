package depromeet.domain.challenge.domain;


import lombok.Getter;

@Getter
public enum FoodDetailType {
    COFFEE(DefaultImageProperties.coffee, ThumbImageProperties.coffee),
    DELIVERY(DefaultImageProperties.delivery, ThumbImageProperties.delivery);

    private final String defaultUrl;
    private final String thumbUrl;

    FoodDetailType(String defaultUrl, String thumbUrl) {
        this.defaultUrl = defaultUrl;
        this.thumbUrl = thumbUrl;
    }
}
