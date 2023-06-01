package depromeet.api.domain.auth.mapper;


import depromeet.api.domain.auth.dto.response.KakaoAuthResponse;
import depromeet.common.annotation.Mapper;

@Mapper
public class AuthMapper {
    public KakaoAuthResponse toKakaoAuthResponse(String accessToken) {
        return KakaoAuthResponse.of(accessToken);
    }
}
