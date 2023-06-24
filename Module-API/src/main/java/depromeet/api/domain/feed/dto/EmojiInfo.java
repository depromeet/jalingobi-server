package depromeet.api.domain.feed.dto;


import depromeet.domain.comment.domain.Comment;
import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.record.domain.EmojiType;
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
    private Long CRAZY;

    @Schema(description = "REGRETFUL", example = "0")
    private Long REGRETFUL;

    @Schema(description = "WELLDONE", example = "3")
    private Long WELLDONE;

    @Schema(description = "댓글수", example = "5")
    private Integer comment;

    public EmojiInfo(Record record, Long myUserChallengeId) {
        this(record);

        Optional<Emoji> selected =
                record.getEmojis().stream()
                        .filter((e) -> e.getUserChallenge().getId() == myUserChallengeId)
                        .findAny();

        if (selected.isPresent()) this.selected = selected.get().toString();
    }

    public EmojiInfo(Record record) {
        Map<String, Long> emojiCountMap =
                record.getEmojis().stream()
                        .map(Emoji::getType)
                        .collect(
                                Collectors.groupingBy(
                                        emojiType -> emojiType.toString(), Collectors.counting()));
        CRAZY = emojiCountMap.get(EmojiType.CRAZY.toString());
        REGRETFUL = emojiCountMap.get(EmojiType.REGRETFUL.toString());
        WELLDONE = emojiCountMap.get(EmojiType.WELLDONE.toString());

        List<Comment> comments = record.getComments();
        this.comment = comments.size();
    }
}
