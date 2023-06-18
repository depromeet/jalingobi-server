package depromeet.api.domain.challenge.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeadCountResponse {

    private int availableCount;

    private int participants;
}
