package depromeet.api.domain.guest.usecase;


import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.api.domain.guest.mapper.GuestMapper;
import depromeet.api.util.JwtUtil;
import depromeet.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class ExperienceGuestModeUseCase {

    private final JwtUtil jwtUtil;
    private final GuestMapper guestMapper;

    public ExperienceGuestModeResponse execute() {
        String guestToken = jwtUtil.issueGuestToken();

        return guestMapper.toExperienceGuestModeResponse(guestToken);
    }
}
