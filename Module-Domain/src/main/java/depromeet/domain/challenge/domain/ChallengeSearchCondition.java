package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChallengeSearchCondition {

    private CategoryType category;
    private String filter;
    private String sortType;
}
