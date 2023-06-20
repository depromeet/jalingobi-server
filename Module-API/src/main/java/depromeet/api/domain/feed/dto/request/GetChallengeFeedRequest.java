package depromeet.api.domain.feed.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetChallengeFeedRequest {

    @Schema(description = "첫 화면 요청시 0으로 설정. 이후부터는 lastRecordId 값으로 설정")
    @NotNull(message = "offsetRecordId는 비어있을 수 없습니다.")
    @Min(value = 0, message = "offsetRecordId는 0이상의 값입니다.")
    private Long offsetRecordId;
}
