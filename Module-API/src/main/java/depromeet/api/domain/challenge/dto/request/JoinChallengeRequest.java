package depromeet.api.domain.challenge.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinChallengeRequest {

    @Schema(description = "챌린지에서 사용할 닉네임", example = "눈누난나")
    private String nickname;

    @Schema(description = "프로필 이미지", example = "/test.jpg")
    private String imgUrl;

    public void CreateJoinChallengeRequest(String nickname, String imgUrl) {
        this.imgUrl = imgUrl;
        this.nickname = nickname;
    }
}
