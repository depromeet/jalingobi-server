package depromeet.api.domain.challenge.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRandomNicknameResponse {

    @Schema(description = "랜덤 닉네임", example = "거지의 민족")
    private String nickname;

    public static CreateRandomNicknameResponse of(String nickname) {
        return CreateRandomNicknameResponse.builder().nickname(nickname).build();
    }
}
