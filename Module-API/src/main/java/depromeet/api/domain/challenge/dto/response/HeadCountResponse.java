package depromeet.api.domain.challenge.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeadCountResponse {

    @Schema(description = "챌린지 최대 수용 인원", example = "50")
    private int availableCount;

    @Schema(description = "현재 챌린지 참가 인원", example = "30")
    private int participants;
}
