package depromeet.api.domain.auth.feign.dto;


import lombok.Data;

@Data
public class KakaoUserInfo {

    private String sub;

    private String nickname;

    private String email;

    private String email_verified;

    private String gender;

    private String birthdate;
}
