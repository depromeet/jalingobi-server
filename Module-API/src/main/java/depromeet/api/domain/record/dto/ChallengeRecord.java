package depromeet.api.domain.record.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ChallengeRecord {

    @Schema(description = "본인 기록인지", example = "true")
    private Boolean isMine;

    @Schema(example = "사용자 닉네임")
    private String nickname;

    @Schema(example = "사용자 이미지 URL")
    private String userImgUrl;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;

    @Schema(example = "기록 이미지 URL")
    private String recordImgUrl;

    @Schema(example = "지출 내역명")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(description = "가격", example = "5000")
    private Integer price;
}
