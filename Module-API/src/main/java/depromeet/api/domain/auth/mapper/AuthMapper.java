package depromeet.api.domain.auth.mapper;


import depromeet.api.domain.auth.dto.TokenInfo;
import depromeet.api.domain.auth.dto.response.KakaoAuthResponse;
import depromeet.common.annotation.Mapper;

@Mapper
public class AuthMapper {
    public KakaoAuthResponse toKakaoAuthResponse(TokenInfo tokenInfo) {
        return KakaoAuthResponse.of(tokenInfo.getRefreshToken(), tokenInfo.getAccessToken());
    }
}
