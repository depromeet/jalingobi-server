package depromeet.common.temp;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenInfo {

    private String accessToken;

    private String refreshToken;
}
