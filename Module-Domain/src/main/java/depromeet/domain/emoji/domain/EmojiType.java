package depromeet.domain.emoji.domain;


import depromeet.domain.emoji.exception.EmojiNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmojiType {
    WELLDONE("WELLDONE"),
    REGRETFUL("REGRETFUL"),
    CRAZY("CRAZY");

    private String value;

    public static EmojiType of(String source) {
        return Arrays.stream(EmojiType.values())
                .filter(value -> value.getValue().equals(source))
                .findAny()
                .orElseThrow(() -> EmojiNotFoundException.EXCEPTION);
    }
}
