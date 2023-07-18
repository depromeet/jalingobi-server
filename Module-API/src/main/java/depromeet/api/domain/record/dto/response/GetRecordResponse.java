package depromeet.api.domain.record.dto.response;


import depromeet.api.domain.feed.dto.EmojiInfo;
import depromeet.api.domain.record.dto.CommentInfo;
import depromeet.api.domain.record.dto.RecordInfo;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetRecordResponse {

    @Schema(description = "본인 기록인지", example = "true")
    private Boolean isMine;

    private RecordUserInfo userInfo;

    private RecordInfo recordInfo;

    private EmojiInfo emojiInfo;

    private List<CommentInfo> commentInfoList;

    public static GetRecordResponse of(Record record, Long myUserChallengeId) {

        Boolean isMine = record.getUserChallenge().getId() == myUserChallengeId;

        RecordUserInfo userInfo = new RecordUserInfo(record.getUserChallenge());
        RecordInfo recordInfo = new RecordInfo(record);
        EmojiInfo emojiInfo = new EmojiInfo(record);

        List<CommentInfo> commentInfoList =
                record.getComments().stream()
                        .map((comment -> CommentInfo.createCommentInfo(comment, myUserChallengeId)))
                        .collect(Collectors.toList());

        return GetRecordResponse.builder()
                .isMine(isMine)
                .userInfo(userInfo)
                .recordInfo(recordInfo)
                .emojiInfo(emojiInfo)
                .commentInfoList(commentInfoList)
                .build();
    }

    @Data
    private static class RecordUserInfo {
        @Schema(example = "사용자 닉네임")
        private String nickname;

        @Schema(example = "사용자 이미지 URL")
        private String imgUrl;

        public RecordUserInfo(UserChallenge userChallenge) {
            this.nickname = userChallenge.getNickname();
            this.imgUrl = userChallenge.getImgUrl();
        }
    }
}
