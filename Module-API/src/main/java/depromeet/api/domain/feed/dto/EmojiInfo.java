package depromeet.api.domain.feed.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.emoji.domain.EmojiType;
import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class EmojiInfo {
    @Schema(description = "내가 선택한 이모지", example = "CRAZY")
    private String selected = null;

    @Schema(description = "CRAZY", example = "2")
    @JsonProperty("CRAZY")
    private Long crazy;

    @Schema(description = "REGRETFUL", example = "0")
    @JsonProperty("REGRETFUL")
    private Long regretful;

    @Schema(description = "WELLDONE", example = "3")
    @JsonProperty("WELLDONE")
    private Long wellDone;

    @Schema(description = "댓글수", example = "5")
    private Integer comment;

    public EmojiInfo(Record record, String selected) {
        Map<String, Long> emojiCountMap =
                record.getEmojis().stream()
                        .map(Emoji::getType)
                        .collect(
                                Collectors.groupingBy(
                                        emojiType -> emojiType, Collectors.counting()));
        crazy = emojiCountMap.getOrDefault(EmojiType.CRAZY.toString(), 0L);
        regretful = emojiCountMap.getOrDefault(EmojiType.REGRETFUL.toString(), 0L);
        wellDone = emojiCountMap.getOrDefault(EmojiType.WELLDONE.toString(), 0L);

        List<Comment> comments = record.getComments();
        this.comment = comments.size();
        this.selected = selected;
    }

    public static EmojiInfo createMyRoomEmojiInfo(Record record, Long userId) {
        Optional<Emoji> selected =
                record.getEmojis().stream()
                        .filter((e) -> e.getUserChallenge().getUser().getId() == userId)
                        .findAny();

        String selectedEmojiType = null;
        if (selected.isPresent()) {
            Emoji selectedEmoji = selected.get();
            selectedEmojiType = selectedEmoji.getType();
        }

        return new EmojiInfo(record, selectedEmojiType);
    }

    public static EmojiInfo createChallengeRoomEmojiInfo(Record record, Long userChallengeId) {
        Optional<Emoji> selected =
                record.getEmojis().stream()
                        .filter((e) -> e.getUserChallenge().getId() == userChallengeId)
                        .findAny();

        String selectedEmojiType = null;
        if (selected.isPresent()) {
            Emoji selectedEmoji = selected.get();
            selectedEmojiType = selectedEmoji.getType();
        }

        return new EmojiInfo(record, selectedEmojiType);
    }

    public static EmojiInfo createRecordEmojiInfo(Record record, Long userChallengeId) {
        Optional<Emoji> selected =
                record.getEmojis().stream()
                        .filter((e) -> e.getUserChallenge().getId() == userChallengeId)
                        .findAny();

        String selectedEmojiType = null;
        if (selected.isPresent()) {
            Emoji selectedEmoji = selected.get();
            selectedEmojiType = selectedEmoji.getType();
        }

        return new EmojiInfo(record, selectedEmojiType);
    }
}
