package depromeet.api.domain.auth.dto.request;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class KakaoAuthRequest {

    @NotBlank(message = "잘못된 요청값입니다.")
    private String idToken;

    @NotBlank(message = "잘못된 요청값입니다.")
    private String accessToken;
}
