package depromeet.api.domain.emoji.dto.request;


import depromeet.common.annotation.ValidEnum;
import depromeet.domain.emoji.domain.EmojiType;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmojiRequest {

    @NotNull(message = "이모지 타입을 입력하세요.")
    @Schema(description = "WELLDONE | REGRETFUL | CRAZY")
    @ValidEnum(enumClass = EmojiType.class, message = "유효하지 않은 이모지 파라미터입니다.")
    private EmojiType type;
}
