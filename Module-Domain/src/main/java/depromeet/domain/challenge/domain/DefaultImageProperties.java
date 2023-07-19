package depromeet.domain.challenge.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultImageProperties {

    public static String food;
    public static String coffee;
    public static String delivery;
    public static String hobby;
    public static String fashionBeauty;
    public static String transportation;

    @Value("${image.default.category.food}")
    public void setFood(String food) {
        this.food = food;
    }

    @Value("${image.default.category.coffee}")
    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    @Value("${image.default.category.delivery}")
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Value("${image.default.category.hobby}")
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Value("${image.default.category.fashion-beauty}")
    public void setFashionBeauty(String fashionBeauty) {
        this.fashionBeauty = fashionBeauty;
    }

    @Value("${image.default.category.transportation}")
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }
}
