package depromeet.api.domain.auth.dto.request;


import lombok.Data;

@Data
public class AuthRequest {
    private String idToken;

    private String accessToken;
}
