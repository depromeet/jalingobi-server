package depromeet.api.domain.comment.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateCommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private long commentId;
}
