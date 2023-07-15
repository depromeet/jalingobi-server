package depromeet.api.domain.comment.dto.response;


import depromeet.domain.comment.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 작성자의 ID", example = "1")
    private Long commenterId;

    @Schema(description = "댓글을 쓴 유저의 프로필 이미지", example = "/test.jpg")
    private String imgUrl;

    @Schema(description = "유저의 닉네임", example = "자린고비")
    private String nickname;

    @Schema(description = "댓글 내용", example = "안녕")
    private String content;

    @Schema(description = "댓글 작성 일자")
    private LocalDateTime commentDate;

    public static CreateCommentResponse of(Comment comment, String imgUrl, String nickname) {
        return CreateCommentResponse.builder()
                .id(comment.getId())
                .commenterId(comment.getUserChallenge().getId())
                .content(comment.getContent())
                .imgUrl(imgUrl)
                .nickname(nickname)
                .commentDate(comment.getCreatedAt())
                .build();
    }
}
