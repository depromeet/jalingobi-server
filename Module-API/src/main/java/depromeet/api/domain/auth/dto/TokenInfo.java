package depromeet.api.domain.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenInfo {

    private String accessToken;

    private String refreshToken;
}
