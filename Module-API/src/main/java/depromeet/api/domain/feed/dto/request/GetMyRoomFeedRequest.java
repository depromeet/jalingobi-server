package depromeet.api.domain.feed.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetMyRoomFeedRequest {

    @Schema(description = "0 부터 시작해서 limit 만큼 더해가면서 요청")
    @NotNull(message = "offset은 비어있을 수 없습니다.")
    @Min(value = 0, message = "offset은 0 이상의 값입니다.")
    private Integer offset;
}
