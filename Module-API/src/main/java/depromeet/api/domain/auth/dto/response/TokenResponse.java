package depromeet.api.domain.auth.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenResponse {

    @JsonIgnore private String refreshToken;

    private String accessToken;

    public static TokenResponse of(String refreshToken, String accessToken) {
        return new TokenResponse(refreshToken, accessToken);
    }

    public Boolean isExistRefreshToken() {
        return (refreshToken != null);
    }
}
