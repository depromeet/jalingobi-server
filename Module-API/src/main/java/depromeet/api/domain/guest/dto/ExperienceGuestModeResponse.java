package depromeet.api.domain.guest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExperienceGuestModeResponse {
    private String accessToken;

    public static ExperienceGuestModeResponse of(String guestToken) {
        return new ExperienceGuestModeResponse(guestToken);
    }
}
