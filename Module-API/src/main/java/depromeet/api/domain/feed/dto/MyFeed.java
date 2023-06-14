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

    private RecordInfo recordInfo;

    private ChallengeInfo challengeInfo;

    private EmojiInfo emojiInfo;

    public static MyFeed createMyFeed(Record record) {
        RecordInfo recordInfo = new RecordInfo(record);
        ChallengeInfo challengeInfo = new ChallengeInfo(record.getChallenge());
        EmojiInfo emojiInfo = new EmojiInfo(record);

        return MyFeed.builder()
                .recordInfo(recordInfo)
                .challengeInfo(challengeInfo)
                .emojiInfo(emojiInfo)
                .build();
    }

    @Data
    public static class ChallengeInfo {
        @Schema(example = "챌린지 이미지 URL")
        private String imgUrl;

        @Schema(example = "챌린지 타이틀")
        private String title;

        public ChallengeInfo(Challenge challenge) {
            this.imgUrl = challenge.getImgUrl();
            this.title = challenge.getTitle();
        }
    }
}
