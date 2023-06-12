package depromeet.api.domain.mypage.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    @Schema(example = "사용자 닉네임")
    @NotNull(message = "닉네임을 입력해주세요.")
    @Size(min = 1, max = 20, message = "닉네임은 20자 이내입니다.")
    private String nickName;

    @Schema(example = "사용자 프로필 URL")
    @NotNull(message = "이미지 url을 입력해주세요.")
    private String profileImgUrl;
}
