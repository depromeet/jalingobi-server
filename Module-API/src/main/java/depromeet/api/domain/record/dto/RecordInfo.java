package depromeet.api.domain.record.dto;


import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordInfo {

    @Schema(description = "기록 Id", example = "27")
    private Long recordId;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;

    @Schema(example = "기록 이미지 URL")
    private String imgUrl;

    @Schema(example = "지출 내역명")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    public RecordInfo(Record record) {
        this.recordId = record.getId();
        this.recordDate = record.getCreatedAt();
        this.imgUrl = record.getImgUrl();
        this.title = record.getTitle();
        this.content = record.getContent();
        this.price = record.getPrice();
    }
}
