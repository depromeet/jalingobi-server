package depromeet.api.domain.guest.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ExperienceGuestModeResponse {
    private String accessToken;

    public static ExperienceGuestModeResponse of(String guestToken) {
        return new ExperienceGuestModeResponse(guestToken);
    }
}
