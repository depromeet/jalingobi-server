package depromeet.api.domain.feed.dto;


import depromeet.domain.record.domain.Record;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FeedRecordInfo {
    @Schema(description = "기록 ID", example = "27")
    private Long id;

    @Schema(example = "기록 이미지 URL")
    private String imgUrl;

    @Schema(example = "기록 타이틀")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(description = "기록 작성일")
    private LocalDateTime date;

    public FeedRecordInfo(Record record) {
        this.id = record.getId();
        this.imgUrl = record.getImgUrl();
        this.title = record.getTitle();
        this.content = record.getContent();
        this.price = record.getPrice();
        this.date = record.getCreatedAt();
    }
}
