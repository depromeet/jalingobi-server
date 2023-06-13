package depromeet.api.domain.emoji.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateEmojiRequest {

    private String type;
}
