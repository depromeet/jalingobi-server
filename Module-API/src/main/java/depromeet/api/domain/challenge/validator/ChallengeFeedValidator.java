package depromeet.api.domain.challenge.validator;


import depromeet.domain.challenge.domain.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChallengeFeedValidator {

    public void validateCategory(String category) {
        if (!category.isBlank()) CategoryType.of(category);
    }
}
