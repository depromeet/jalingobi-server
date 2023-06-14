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
    @Schema(description = "내가 선택한 이모지", example = "이모지 종류 - 없을경우 null 값으로할지 아예 데이터 보내주지 않을지 결정")
    private String selectedEmoji = null;

    @Schema(description = "미친거지", example = "2")
    private Long crazy;

    @Schema(description = "후회할거지", example = "0")
    private Long regretful;

    @Schema(description = "잘할거지", example = "3")
    private Long wellDone;

    @Schema(description = "댓글수", example = "5")
    private Integer comment;

    public EmojiInfo(Record record, Long myUserChallengeId) {
        this(record);

        Optional<Emoji> selectedEmoji =
                record.getEmojis().stream()
                        .filter((e) -> e.getUserChallenge().getId() == myUserChallengeId)
                        .findAny();

        if (selectedEmoji.isPresent())
            this.selectedEmoji = selectedEmoji.get().getType().getValue();
        else this.selectedEmoji = null;
    }

    public EmojiInfo(Record record) {
        Map<String, Long> emojiCountMap =
                record.getEmojis().stream()
                        .map(Emoji::getType)
                        .collect(Collectors.groupingBy(EmojiType::getValue, Collectors.counting()));
        this.crazy = emojiCountMap.get(EmojiType.CRAZY_BEGGAR.getValue());
        this.regretful = emojiCountMap.get(EmojiType.REGRETFUL_BEGGAR.getValue());
        this.wellDone = emojiCountMap.get(EmojiType.WELL_DONE_BEGGAR.getValue());

        List<Comment> comments = record.getComments();
        this.comment = comments.size();
    }
}
