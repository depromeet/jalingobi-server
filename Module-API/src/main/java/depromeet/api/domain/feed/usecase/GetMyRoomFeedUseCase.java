package depromeet.api.domain.feed.usecase;


import depromeet.api.domain.feed.dto.response.GetMyRoomFeedResponse;
import depromeet.api.domain.feed.mapper.RecordListMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.record.adaptor.RecordAdaptor;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetMyRoomFeedUseCase {

    private final RecordAdaptor recordAdaptor;
    private final UserAdaptor userAdaptor;
    private final RecordListMapper recordListMapper;
    private final Integer LIMIT = 20;

    public GetMyRoomFeedResponse execute(String socialId, Integer offset) {

        User user = userAdaptor.findUser(socialId);
        List<Record> myRecordList = recordAdaptor.findMyRecordList(user.getId(), offset, LIMIT);
        Integer total = recordAdaptor.countMyRecordList(user.getId());

        return recordListMapper.toGetMyRoomFeedResponse(myRecordList, total, LIMIT);
    }
}
