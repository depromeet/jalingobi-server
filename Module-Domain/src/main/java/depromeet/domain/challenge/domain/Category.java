package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Category {
    FOOD("식비"),
    HOBBY_LEISURE("취미/여가"),
    FASHION_BEAUTY("패션/뷰티"),
    TRANSPORTATION_AUTOMOBILE("교통/택시");

    private String name;
}
