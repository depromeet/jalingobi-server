package depromeet.domain.jalingobi.domain;


import depromeet.domain.jalingobi.exception.LevelNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    LEVEL_0(0),
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    LEVEL_6(6);

    private final int score;

    public static Level getEnumTypeByScore(int score) {
        return Arrays.stream(Level.values())
                .filter(type -> type.getScore() == score)
                .findFirst()
                .orElseThrow(() -> LevelNotFoundException.EXCEPTION);
    }
}
