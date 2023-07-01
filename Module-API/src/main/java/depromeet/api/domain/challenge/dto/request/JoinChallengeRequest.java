package depromeet.api.domain.challenge.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinChallengeRequest {

    @NotBlank(message = "닉네임을 입력하세요.")
    @Size(min = 1, max = 20, message = "닉네임은 20자 이하입니다.")
    @Schema(description = "챌린지에서 사용할 닉네임", example = "눈누난나")
    private String nickname;

    @NotBlank(message = "프로필 이미지 경로를 입력하세요.")
    @Schema(description = "프로필 이미지", example = "/test.jpg")
    private String imgUrl;

    public void CreateJoinChallengeRequest(String nickname, String imgUrl) {
        this.imgUrl = imgUrl;
        this.nickname = nickname;
    }
}
