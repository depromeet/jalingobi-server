package depromeet.api.domain.record.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordReply {

    @Schema(description = "본인 댓글인지", example = "true")
    private Boolean isMine;

    @Schema(example = "댓글 작성자 닉네임")
    private String nickname;

    @Schema(example = "댓글 작성자 이미지 URL")
    private String imgUrl;

    @Schema(example = "댓글 내용")
    private String content;

    @Schema(description = "댓글 작성일")
    private LocalDateTime replyDate;
}
