package depromeet.api.domain.challenge.dto.response;


import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateChallengeResponse {

    private List<String> categories;
    private String title;
    private Integer price;
    private String imgUrl;
    private List<String> keywords;
    private Integer availableCount;
    private List<String> rules;
    private Integer period;
    private LocalDate startAt;
    private LocalDate endAt;

    public static UpdateChallengeResponse of(UpdateChallengeRequest updateChallengeRequest) {
        return UpdateChallengeResponse.builder()
                .categories(updateChallengeRequest.getCategories())
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
