package depromeet.api.domain.jalingobi.dto.request;


import depromeet.common.annotation.ValidEnum;
import depromeet.domain.jalingobi.domain.Level;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSmallTalkRequest {

    @Schema(description = "Small Talk이 필요한 자린고비 레벨")
    @NotBlank(message = "자린고비 레벨을 입력해주세요.")
    @ValidEnum(enumClass = Level.class, message = "유효하지 않은 Level 값 입니다.")
    private Level level;
}
