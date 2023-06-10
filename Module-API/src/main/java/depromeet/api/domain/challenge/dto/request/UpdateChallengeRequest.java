package depromeet.api.domain.challenge.dto.request;


import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeRequest {

    private Long ChallengeId;
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
}
