package depromeet.api.domain.feed.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MyFeed {

    @Schema(example = "27")
    private Long recordId;

    @Schema(example = "사용자 닉네임")
    private String nickname;

    @Schema(example = "이미지 URL")
    private String imgUrl;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(example = "기록 타이틀")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(example = "챌린지 타이틀")
    private String challengeTitle;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;
}
