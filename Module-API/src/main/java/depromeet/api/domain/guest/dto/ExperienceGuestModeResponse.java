package depromeet.api.domain.guest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExperienceGuestModeResponse {

    @Schema(example = "게스트 모드용 accessToken")
    private String accessToken;

    public static ExperienceGuestModeResponse of(String guestToken) {
        return new ExperienceGuestModeResponse(guestToken);
    }
}
