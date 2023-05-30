package depromeet.api.domain.record.dto.request;


import depromeet.common.annotation.ValidEnum;
import depromeet.domain.record.domain.RecordEvaluation;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateRecordRequest {
    private int price;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 80)
    private String content;

    private String imgUrl;

    @ValidEnum(enumClass = RecordEvaluation.class)
    private RecordEvaluation evaluation;
}
