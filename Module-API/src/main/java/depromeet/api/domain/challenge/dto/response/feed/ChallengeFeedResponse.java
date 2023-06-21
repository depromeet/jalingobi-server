package depromeet.api.domain.challenge.dto.response.feed;


import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeFeedResponse {

    private Long challengeId;

    private Integer curCount;

    private Integer availableCount;

    private String title;

    private String imgUrl;

    private List<String> keywords;

    private LocalDate startAt;

    private Integer period;
}
