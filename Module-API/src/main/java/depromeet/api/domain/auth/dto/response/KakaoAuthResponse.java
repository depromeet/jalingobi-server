package depromeet.api.domain.auth.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoAuthResponse {
    private String accessToken;

    public static KakaoAuthResponse of(String accessToken) {
        return new KakaoAuthResponse(accessToken);
    }
}
