package depromeet.api.domain.feed.mapper;


import depromeet.api.domain.feed.dto.response.GetChallengeFeedResponse;
import depromeet.api.domain.feed.dto.response.GetMyRoomFeedResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.record.domain.Record;
import java.util.List;

@Mapper
public class RecordListMapper {
    public GetMyRoomFeedResponse toGetMyRoomFeedResponse(
            List<Record> recordList, Integer total, Integer limit) {
        return GetMyRoomFeedResponse.of(recordList, total, limit);
    }

    public GetChallengeFeedResponse toGetChallengeFeedResponse(
            List<Record> recordList, Long myUserChallengeId, Integer total, Integer limit) {
        return GetChallengeFeedResponse.of(recordList, myUserChallengeId, total, limit);
    }
}
