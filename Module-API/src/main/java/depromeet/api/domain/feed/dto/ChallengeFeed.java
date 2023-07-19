package depromeet.api.domain.feed.dto;


import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ChallengeFeed {

    @Schema(description = "본인 기록인지", example = "true")
    private Boolean isMine;

    private FeedUserInfo userInfo;

    private FeedRecordInfo recordInfo;

    private EmojiInfo emojiInfo;

    public static ChallengeFeed createChallengeFeed(Record record, Long userChallengeId) {
        Boolean isMine = record.getUserChallenge().getId() == userChallengeId;

        FeedUserInfo userInfo = new FeedUserInfo(record.getUserChallenge());
        FeedRecordInfo recordInfo = new FeedRecordInfo(record);
        EmojiInfo emojiInfo = EmojiInfo.createChallengeRoomEmojiInfo(record, userChallengeId);

        return ChallengeFeed.builder()
                .isMine(isMine)
                .userInfo(userInfo)
                .recordInfo(recordInfo)
                .emojiInfo(emojiInfo)
                .build();
    }

    @Builder
    @AllArgsConstructor
    @Data
    public static class FeedUserInfo {
        @Schema(example = "유저 이미지 URL")
        private String imgUrl;

        @Schema(example = "사용자 닉네임")
        private String nickname;

        @Schema(description = "다른 유저의 현재 지출액", example = "78000")
        private Integer currentCharge;

        public FeedUserInfo(UserChallenge userChallenge) {
            this.imgUrl = userChallenge.getImgUrl();
            this.nickname = userChallenge.getNickname();
            this.currentCharge = userChallenge.getCurrentCharge();
        }
    }
}
