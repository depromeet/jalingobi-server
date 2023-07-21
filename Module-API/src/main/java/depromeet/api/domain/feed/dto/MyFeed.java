package depromeet.api.domain.feed.dto;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MyFeed {

    private FeedRecordInfo recordInfo;

    private ChallengeInfo challengeInfo;

    private EmojiInfo emojiInfo;

    public static MyFeed createMyFeed(Record record, Long userId) {
        FeedRecordInfo recordInfo = new FeedRecordInfo(record);
        ChallengeInfo challengeInfo = new ChallengeInfo(record.getChallenge());
        EmojiInfo emojiInfo = EmojiInfo.createMyRoomEmojiInfo(record, userId);

        return MyFeed.builder()
                .recordInfo(recordInfo)
                .challengeInfo(challengeInfo)
                .emojiInfo(emojiInfo)
                .build();
    }

    @Builder
    @AllArgsConstructor
    @Data
    public static class ChallengeInfo {

        @Schema(description = "챌린지 ID", example = "5")
        private Long id;

        @Schema(example = "챌린지 이미지 URL")
        private String imgUrl;

        @Schema(example = "챌린지 타이틀")
        private String title;

        public ChallengeInfo(Challenge challenge) {
            this.id = challenge.getId();
            this.imgUrl = challenge.getImgUrl().getThumbUrl();
            this.title = challenge.getTitle();
        }
    }
}
