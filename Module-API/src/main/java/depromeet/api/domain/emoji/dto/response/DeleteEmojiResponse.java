package depromeet.api.domain.emoji.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteEmojiResponse {

    private int emojisCount;
    private Boolean selected;

    private DeleteEmojiResponse() {}

    public DeleteEmojiResponse(int emojisCount, Boolean selected) {
        this.emojisCount = emojisCount;
        this.selected = selected;
    }
}
