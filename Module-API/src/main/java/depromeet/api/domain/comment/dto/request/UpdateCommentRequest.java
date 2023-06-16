package depromeet.api.domain.comment.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCommentRequest {

    @Schema(description = "댓글 내용", example = "안녕")
    @NotBlank(message = "댓글을 입력하세요.")
    private String content;

    private UpdateCommentRequest() {}

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
