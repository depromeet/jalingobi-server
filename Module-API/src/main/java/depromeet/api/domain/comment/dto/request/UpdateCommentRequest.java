package depromeet.api.domain.comment.dto.request;


import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCommentRequest {

    @NotBlank(message = "댓글을 입력하세요.")
    private String content;

    private UpdateCommentRequest() {}

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
