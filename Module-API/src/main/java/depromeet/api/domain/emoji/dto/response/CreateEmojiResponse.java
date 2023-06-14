package depromeet.api.domain.emoji.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateEmojiResponse {

    private int emojisCount;
    private String type;
    private Boolean selected;

    private CreateEmojiResponse() {}

    public CreateEmojiResponse(int emojisCount, String type, Boolean selected) {
        this.emojisCount = emojisCount;
        this.type = type;
        this.selected = selected;
    }
}
