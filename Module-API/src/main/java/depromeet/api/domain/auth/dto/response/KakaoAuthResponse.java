package depromeet.api.domain.auth.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoAuthResponse {

    @JsonIgnore private String refreshToken;

    private String accessToken;

    public static KakaoAuthResponse of(String refreshToken, String accessToken) {
        return new KakaoAuthResponse(refreshToken, accessToken);
    }
}
