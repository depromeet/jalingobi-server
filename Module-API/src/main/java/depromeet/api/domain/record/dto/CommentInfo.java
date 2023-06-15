package depromeet.api.domain.record.dto;


import depromeet.domain.comment.domain.Comment;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CommentInfo {

    @Schema(description = "본인 댓글인지", example = "true")
    private Boolean isMine;

    @Schema(example = "35")
    private Long commenterId;

    @Schema(example = "댓글 작성자 닉네임")
    private String nickname;

    @Schema(example = "댓글 작성자 이미지 URL")
    private String imgUrl;

    @Schema(example = "27")
    private Long commentId;

    @Schema(example = "댓글 내용")
    private String content;

    @Schema(description = "댓글 작성일")
    private LocalDateTime commentDate;

    public static CommentInfo createCommentInfo(Comment comment, Long myUserChallengeId) {

        // todo: 쿼리 개수 확인
        UserChallenge userChallenge = comment.getUserChallenge();
        Boolean isMine = userChallenge.getId() == myUserChallengeId;

        return CommentInfo.builder()
                .isMine(isMine)
                .commenterId(userChallenge.getId())
                .nickname(userChallenge.getNickname())
                .imgUrl(userChallenge.getImgUrl())
                .commentId(comment.getId())
                .content(comment.getContent())
                .commentDate(comment.getCreatedAt())
                .build();
    }
}
