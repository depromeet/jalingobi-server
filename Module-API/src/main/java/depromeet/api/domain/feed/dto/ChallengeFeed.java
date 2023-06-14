package depromeet.api.domain.feed.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.record.domain.EmojiType;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ChallengeFeed {

    @Schema(description = "본인 기록인지", example = "true")
    private Boolean isMine;

    private UserInfo userInfo;

    private RecordInfo recordInfo;

    private EmojiInfo emojiInfo;

    public static ChallengeFeed createChallengeFeed(Record record, Long myUserChallengeId) {
        Boolean isMine = record.getUserChallenge().getId() == myUserChallengeId;

        UserInfo userInfo = new UserInfo(record.getUserChallenge());
        RecordInfo recordInfo = new RecordInfo(record);
        EmojiInfo emojiInfo = new EmojiInfo(record, myUserChallengeId);

        return ChallengeFeed.builder()
                .isMine(isMine)
                .userInfo(userInfo)
                .recordInfo(recordInfo)
                .emojiInfo(emojiInfo)
                .build();
    }

    public static class UserInfo {
        @Schema(example = "유저 이미지 URL")
        private String userImgUrl;

        @Schema(example = "사용자 닉네임")
        private String nickname;

        @Schema(description = "다른 유저의 현재 지출액", example = "78000")
        private Integer currentCharge;

        public UserInfo(UserChallenge userChallenge) {
            this.userImgUrl = userChallenge.getImgUrl();
            this.nickname = userChallenge.getNickname();
            this.currentCharge = userChallenge.getCurrentCharge();
        }
    }

    private static class RecordInfo {
        @Schema(description = "지출 기록 ID", example = "27")
        private Long recordId;

        @Schema(example = "기록 이미지 URL")
        private String recordImgUrl;

        @Schema(example = "기록 타이틀")
        private String title;

        @Schema(example = "기록 내용")
        private String content;

        @Schema(description = "가격", example = "5000")
        private Integer price;

        @Schema(description = "기록 작성일")
        private LocalDateTime recordDate;

        public RecordInfo(Record record) {
            this.recordId = record.getId();
            this.recordImgUrl = record.getImgUrl();
            this.title = record.getTitle();
            this.content = record.getContent();
            this.price = record.getPrice();
            this.recordDate = record.getCreatedAt();
        }
    }

    private static class EmojiInfo {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String selectedEmoji;

        @Schema(description = "미친거지", example = "2")
        private Long crazy;

        @Schema(description = "후회할거지", example = "0")
        private Long regretful;

        @Schema(description = "잘할거지", example = "3")
        private Long wellDone;

        @Schema(description = "댓글수", example = "5")
        private Integer comment;

        public EmojiInfo(Record record, Long userChallengeId) {
            Optional<Emoji> emoji =
                    record.getEmojis().stream()
                            .filter((e) -> e.getUserChallenge().getId() == userChallengeId)
                            .findAny();

            if (emoji.isPresent()) this.selectedEmoji = emoji.get().getType().getValue();
            else this.selectedEmoji = null;

            Map<String, Long> emojiCountMap =
                    record.getEmojis().stream()
                            .map(Emoji::getType)
                            .collect(
                                    Collectors.groupingBy(
                                            EmojiType::getValue, Collectors.counting()));
            this.crazy = emojiCountMap.get(EmojiType.CRAZY_BEGGAR.getValue());
            this.regretful = emojiCountMap.get(EmojiType.REGRETFUL_BEGGAR.getValue());
            this.wellDone = emojiCountMap.get(EmojiType.WELL_DONE_BEGGAR.getValue());

            List<Comment> comments = record.getComments();
            this.comment = comments.size();
        }
    }
}
