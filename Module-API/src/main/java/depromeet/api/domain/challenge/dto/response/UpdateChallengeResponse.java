package depromeet.api.domain.challenge.dto.response;


import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateChallengeResponse {

    @Schema(description = "챌린지가 속한 카테고리", example = "FOOD")
    private String category;

    @Schema(description = "챌린지 제목", example = "배달 10만원 이하로 쓰기")
    private String title;

    @Schema(description = "챌린지 목표 금액", example = "100000")
    private Integer price;

    @Schema(description = "챌린지 대표 이미지", example = "/test.jpg")
    private String imgUrl;

    @Schema(description = "키워드", example = "[\"#배달비\", \"#10만원챌린지\"]")
    private List<String> keywords;

    @Schema(description = "챌린지 수용 인원", example = "30")
    private Integer availableCount;

    @Schema(description = "챌린지 규칙", example = "[\"광고 금지\", \"악플 금지\"]")
    private List<String> rules;

    @Schema(description = "챌린지 기간", example = "7")
    private Integer period;

    @Schema(description = "챌린지 시작 일자", example = "2023-06-01")
    private LocalDate startAt;

    @Schema(description = "챌린지 종료 일자", example = "2023-06-07")
    private LocalDate endAt;

    public static UpdateChallengeResponse of(UpdateChallengeRequest updateChallengeRequest) {
        return UpdateChallengeResponse.builder()
                .category(updateChallengeRequest.getCategories().get(0))
                .title(updateChallengeRequest.getTitle())
                .price(updateChallengeRequest.getPrice())
                .imgUrl(updateChallengeRequest.getImgUrl())
                .keywords(updateChallengeRequest.getKeywords())
                .availableCount(updateChallengeRequest.getAvailableCount())
                .rules(updateChallengeRequest.getRules())
                .period(updateChallengeRequest.getPeriod())
                .startAt(updateChallengeRequest.getStartAt())
                .endAt(updateChallengeRequest.getEndAt())
                .build();
    }
}
