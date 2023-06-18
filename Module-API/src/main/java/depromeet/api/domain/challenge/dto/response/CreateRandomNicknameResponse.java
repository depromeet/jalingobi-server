package depromeet.api.domain.challenge.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRandomNicknameResponse {

    private String nickname;

    public static CreateRandomNicknameResponse of(String nickname) {
        return CreateRandomNicknameResponse.builder().nickname(nickname).build();
    }
}
