package depromeet.domain.record.domain;


import depromeet.domain.emoji.exception.EmojiNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Emoji {
    WELL_DONE_BEGGAR("상"),
    REGRETFUL_BEGGAR("중"),
    CRAZY_BEGGAR("하");

    private final String value;

    public static Emoji of(String source) {
        return Arrays.stream(Emoji.values())
                .filter(value -> value.getValue().equals(source))
                .findAny()
                .orElseThrow(() -> EmojiNotFoundException.EXCEPTION);
    }
}
