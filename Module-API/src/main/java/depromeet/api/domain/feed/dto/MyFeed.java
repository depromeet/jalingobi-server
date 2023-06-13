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
public class MyFeed {

    @Schema(description = "기록 Id", example = "27")
    private Long recordId;

    @Schema(example = "기록 이미지 URL")
    private String recordImgUrl;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(example = "기록 타이틀")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(example = "챌린지 이미지 URL")
    private String challengeImgUrl;

    @Schema(example = "챌린지 타이틀")
    private String challengeTitle;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;

    public static MyFeed createMyFeed(Record record) {
        return MyFeed.builder()
                .recordId(record.getId())
                .recordImgUrl(record.getImgUrl())
                .price(record.getPrice())
                .title(record.getTitle())
                .content(record.getContent())
                .challengeImgUrl(record.getChallenge().getImgUrl())
                .challengeTitle(record.getChallenge().getTitle())
                .recordDate(record.getCreatedAt())
                .build();
    }
}
