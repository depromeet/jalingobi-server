package depromeet.domain.record.domain;


import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Evaluation {
    WELLDONE("WELLDONE"),
    REGRETFUL("REGRETFUL"),
    CRAZY("CRAZY"),
    NOTHING("NOTHING");

    private final String value;

    public static Evaluation getEnumTypeByValue(String value) {
        return Arrays.stream(Evaluation.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElse(NOTHING);
    }
}
