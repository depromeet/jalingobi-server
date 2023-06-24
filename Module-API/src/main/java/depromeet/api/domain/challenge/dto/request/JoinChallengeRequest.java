package depromeet.api.domain.challenge.dto.request;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinChallengeRequest {

    @NotBlank(message = "챌린지 동안 사용할 닉네임 입력하세요.")
    private String nickname;

    @NotBlank(message = "프로필 이미지 경로를 입력하세요.")
    private String imgUrl;
}
