package depromeet.api.domain.record.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * @NotNull은 값이 null이 아니어야 하며, @NotBlank는 값이 null이 아니고 비어 있지 않아야 하며, 최소한 하나 이상의 비공백 문자를 포함 기획파트에서 공백
 * 문자도 허용하는지 묻고, 수정하기
 */
@Builder
@Getter
public class CreateRecordRequest {

    @Schema(nullable = false)
    @NotNull(message = "price can not be null")
    private int price;

    @Schema(nullable = false, minimum = "1", maximum = "20", description = "지출 명")
    @NotBlank(message = "name can not be null")
    @Size(min = 1, max = 20)
    private String name;

    @Schema(nullable = false, minimum = "1", maximum = "80", description = "지출 내용")
    @NotBlank(message = "content can not be null")
    @Size(min = 1, max = 80)
    private String content;

    @Schema(nullable = true, description = "사진 링크")
    private String imgUrl;

    @Schema(nullable = false, description = "지출 평가, [1,2,3,4,5] 중에서 선택 가능합니다.")
    @NotNull(message = "evaluation can not be null")
    private int evaluation;
}
