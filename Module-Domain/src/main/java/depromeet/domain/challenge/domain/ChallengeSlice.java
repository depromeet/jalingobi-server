package depromeet.domain.challenge.domain;


import depromeet.domain.challenge.repository.ChallengeData;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChallengeSlice {

    private List<ChallengeData> challenges;
    private boolean hasNext;
}
