package depromeet.api.domain.feed.dto;


import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordInfo {
    @Schema(description = "기록 ID", example = "27")
    private Long recordId;

    @Schema(example = "기록 이미지 URL")
    private String recordImgUrl;

    @Schema(example = "기록 타이틀")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;

    public RecordInfo(Record record) {
        this.recordId = record.getId();
        this.recordImgUrl = record.getImgUrl();
        this.title = record.getTitle();
        this.content = record.getContent();
        this.price = record.getPrice();
        this.recordDate = record.getCreatedAt();
    }
}
