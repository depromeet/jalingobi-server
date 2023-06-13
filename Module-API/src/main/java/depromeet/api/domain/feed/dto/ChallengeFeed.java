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
public class ChallengeFeed {

    @Schema(description = "지출 기록 ID", example = "27")
    private Long recordId;

    @Schema(description = "본인 기록인지", example = "true")
    private Boolean isMine;

    @Schema(description = "다른 유저의 현재 지출액", example = "78000")
    private Integer currentCharge;

    @Schema(example = "유저 이미지 URL")
    private String userImgUrl;

    @Schema(example = "사용자 닉네임")
    private String nickname;

    @Schema(example = "기록 이미지 URL")
    private String recordImgUrl;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(example = "기록 타이틀")
    private String title;

    @Schema(example = "기록 내용")
    private String content;

    @Schema(description = "기록 작성일")
    private LocalDateTime recordDate;

    public static ChallengeFeed createChallengeFeed(Record record) {
        // todo: user_challenge 데이터값으로 교체
        return ChallengeFeed.builder()
                .recordId(record.getId())
                .isMine(false)
                .currentCharge(100)
                .userImgUrl("url")
                .nickname("test")
                .recordImgUrl(record.getImgUrl())
                .price(record.getPrice())
                .title(record.getTitle())
                .content(record.getContent())
                .recordDate(record.getCreatedAt())
                .build();
    }
}
