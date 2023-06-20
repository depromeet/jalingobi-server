package depromeet.api.domain.record.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecordRequest {

    @Schema(minimum = "0", maximum = "999999", example = "지출 비용")
    @NotNull(message = "지출 비용을 입력해주세요.")
    @Range(min = 0, max = 999999, message = "지출 비용은 0부터 999999까지 입력 가능합니다.")
    private Integer price;

    @Schema(minimum = "1", maximum = "16", example = "지출 명")
    @NotBlank(message = "지출 명을 입력해주세요.")
    @Size(min = 1, max = 16, message = "지출 명은 16자 이하입니다.")
    private String title;

    @Schema(minimum = "1", maximum = "80", example = "지출 내용")
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 80, message = "내용은 80자 이하입니다.")
    private String content;

    @Schema(nullable = true, example = "지출 사진 링크")
    private String imgUrl;

    @Schema(description = "지출 평가, [1,2,3,4] 중에서 선택 가능합니다.")
    @NotNull(message = "지출 평가를 입력해주세요.")
    @Range(min = 1, max = 4, message = "점수는 1부터 4까지 입니다.")
    private Integer evaluation;
}