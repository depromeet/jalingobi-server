package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.Feed;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetChallengeFeedResponse {

    @Schema(example = "12")
    private Integer totalPage;

    @Schema(example = "5")
    private Integer currentPage;

    @Schema(description = "페이지 당 기록수", example = "20")
    private Integer perPage;

    private List<Feed> feedList;
}
