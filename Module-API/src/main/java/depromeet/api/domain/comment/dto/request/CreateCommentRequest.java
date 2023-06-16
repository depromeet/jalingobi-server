package depromeet.api.domain.comment.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentRequest {

    @Schema(description = "댓글 내용", example = "안녕")
    @NotBlank(message = "댓글을 입력하세요.")
    private String content;

    private CreateCommentRequest() {}

    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
