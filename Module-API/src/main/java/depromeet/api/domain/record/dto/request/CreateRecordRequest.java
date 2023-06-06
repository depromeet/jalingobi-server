package depromeet.api.domain.record.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @NotNull은 값이 null이 아니어야 하며, @NotBlank는 값이 null이 아니고 비어 있지 않아야 하며, 최소한 하나 이상의 비공백 문자를 포함 기획파트에서 공백
 * 문자도 허용하는지 묻고, 수정하기
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecordRequest {

    @Schema(nullable = false)
    @NotNull(message = "지출 비용을 입력해주세요.")
    @Min(value = 0, message = "지출 비용 최소 값은 0입니다.")
    @Max(value = 999999, message = "지출 비용 최대 값은 999999입니다.")
    private int price;

    @Schema(nullable = false, minimum = "1", maximum = "16", description = "지출 명")
    @NotBlank(message = "지출 명을 입력해주세요.")
    @Size(min = 1, max = 16, message = "지출 명은 16자 이하입니다.")
    private String name;

    @Schema(nullable = false, minimum = "1", maximum = "80", description = "지출 내용")
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 80, message = "내용은 80자 이하입니다.")
    private String content;

    @Schema(nullable = true, description = "사진 링크")
    private String imgUrl;

    @Schema(nullable = false, description = "지출 평가, [1,2,3,4] 중에서 선택 가능합니다.")
    @NotNull(message = "지출 평가를 입력해주세요.")
    @Min(value = 1, message = "점수는 1부터 시작합니다.")
    @Max(value = 4, message = "점수는 4이하 입니다.")
    private int evaluation;
}
