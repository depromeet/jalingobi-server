package depromeet.api.domain.emoji.dto.request;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmojiRequest {

    @NotBlank(message = "이모지 타입을 입력하세요.")
    private String type;
}
