package depromeet.api.domain.comment.dto.response;


import depromeet.domain.comment.domain.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentResponse {

    private Long id;
    private String imgUrl;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    public static CreateCommentResponse of(Comment comment, String imgUrl, String nickname) {
        return CreateCommentResponse.builder()
                .content(comment.getContent())
                .imgUrl(imgUrl)
                .nickname(nickname)
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
