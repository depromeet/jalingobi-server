package depromeet.domain.jalingobi.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import depromeet.domain.jalingobi.exception.LevelNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LevelTest {
    @Test
    @DisplayName("[Level] enum 타입으로 변환하는 테스트")
    void LevelTest() {
        Level zero = Level.getEnumTypeByScore(0);
        Assertions.assertEquals(Level.LEVEL_0, zero);

        Level one = Level.getEnumTypeByScore(1);
        Assertions.assertEquals(Level.LEVEL_1, one);

        Level two = Level.getEnumTypeByScore(2);
        Assertions.assertEquals(Level.LEVEL_2, two);

        Level three = Level.getEnumTypeByScore(3);
        Assertions.assertEquals(Level.LEVEL_3, three);

        Level four = Level.getEnumTypeByScore(4);
        Assertions.assertEquals(Level.LEVEL_4, four);

        Level five = Level.getEnumTypeByScore(5);
        Assertions.assertEquals(Level.LEVEL_5, five);

        Level six = Level.getEnumTypeByScore(6);
        Assertions.assertEquals(Level.LEVEL_6, six);
    }

    @Test
    @DisplayName("[Level] 존재하지 않는 enum 타입")
    void LevelTest2() {
        assertThatThrownBy(() -> Level.getEnumTypeByScore(-2))
                .isInstanceOf(LevelNotFoundException.class);

        assertThatThrownBy(() -> Level.getEnumTypeByScore(15))
                .isInstanceOf(LevelNotFoundException.class);
    }
}
