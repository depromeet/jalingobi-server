package depromeet.api.domain.record.dto.response;


import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateRecordResponse {
    private Long id;
    private String title;
    private String content;
    private String imgUrl;
    private String evaluation;
    private RecordUserInfo userInfo;

    public static CreateRecordResponse of(Record record) {

        RecordUserInfo userInfo = new RecordUserInfo(record.getUserChallenge());

        return CreateRecordResponse.builder()
                .id(record.getId())
                .title(record.getTitle())
                .content(record.getContent())
                .imgUrl(record.getImgUrl())
                .evaluation(record.getEvaluation().getValue())
                .userInfo(userInfo)
                .build();
    }

    @Data
    private static class RecordUserInfo {
        @Schema(example = "사용자 닉네임")
        private String nickname;

        @Schema(example = "사용자 이미지 URL")
        private String userImgUrl;

        public RecordUserInfo(UserChallenge userChallenge) {
            this.nickname = userChallenge.getNickname();
            this.userImgUrl = userChallenge.getImgUrl();
        }
    }
}
