package depromeet.api.domain.comment.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCommentRequest {

    @Schema(description = "댓글 내용", example = "안녕")
    @NotBlank(message = "댓글을 입력하세요.")
    @Size(min = 1, max = 2000, message = "내용은 2,000자 이하입니다.")
    private String content;

    private UpdateCommentRequest() {}

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
