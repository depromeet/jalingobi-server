package depromeet.api.domain.feed.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import depromeet.api.domain.feed.dto.ChallengeFeed;
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
public class GetChallengeFeedResponse {

    @Schema(description = "총 기록 개수", example = "120")
    private Integer total;

    @Schema(description = "최대 기록 개수", example = "20")
    private Integer limit;

    @Schema(description = "이번에 보낸 기록 개수", example = "15")
    private Integer current;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "마지막 기록의 id", example = "38")
    private Long lastRecordId;

    private List<ChallengeFeed> challengeFeedList;

    public static GetChallengeFeedResponse of(
            List<Record> recordList, Long myUserChallengeId, Integer total, Integer limit) {

        Integer recordCount = recordList.size();
        Long lastRecordId = getLastRecordId(recordList, recordCount);

        List<ChallengeFeed> challengeFeeds =
                recordList.stream()
                        .map(
                                (record) ->
                                        ChallengeFeed.createChallengeFeed(
                                                record, myUserChallengeId))
                        .collect(Collectors.toList());

        return GetChallengeFeedResponse.builder()
                .total(total)
                .limit(limit)
                .current(recordCount)
                .lastRecordId(lastRecordId)
                .challengeFeedList(challengeFeeds)
                .build();
    }

    private static Long getLastRecordId(List<Record> recordList, Integer recordCount) {
        if (recordCount > 0) {
            Record lastRecord = recordList.get(recordCount - 1);
            return lastRecord.getId();
        } else return null;
    }
}
