package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.MyFeed;
import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetMyRoomFeedResponse {

    @Schema(description = "총 기록 개수", example = "120")
    private Integer total;

    @Schema(description = "최대 기록 개수", example = "20")
    private Integer limit;

    @Schema(description = "이번에 보낸 기록 개수", example = "15")
    private Integer current;

    private List<MyFeed> myFeedList;

    public static GetMyRoomFeedResponse of(
            List<Record> recordList, Long userId, Integer total, Integer limit) {
        Integer current = recordList.size();

        List<MyFeed> myFeeds =
                recordList.stream()
                        .map(record -> MyFeed.createMyFeed(record, userId))
                        .collect(Collectors.toList());

        return GetMyRoomFeedResponse.builder()
                .total(total)
                .limit(limit)
                .current(current)
                .myFeedList(myFeeds)
                .build();
    }
}
