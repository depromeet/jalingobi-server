package depromeet.api.domain.challenge.dto.request;


import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChallengeRequest {

    @NotNull private List<@NotBlank String> category;

    @NotBlank(message = "title can not be blank")
    private String title;

    @Min(10000)
    @Max(999999)
    @NotNull
    private Integer price;

    private String imageUrl;

    @NotNull private List<@NotBlank String> keywords;

    @Min(5)
    @Max(50)
    @NotNull
    private Integer availableCount;

    private List<String> challengeRule;

    @NotNull private Integer period;

    @NotNull private LocalDate startAt;

    @NotNull private LocalDate endAt;
}
