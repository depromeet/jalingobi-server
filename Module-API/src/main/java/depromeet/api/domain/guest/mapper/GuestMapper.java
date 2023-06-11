package depromeet.api.domain.guest.mapper;


import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.common.annotation.Mapper;

@Mapper
public class GuestMapper {

    public ExperienceGuestModeResponse toExperienceGuestModeResponse(String guestToken) {
        return ExperienceGuestModeResponse.of(guestToken);
    }
}
