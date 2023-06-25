package depromeet.api.domain.challenge.dto.response;


import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    @Schema(description = "유저의 프로필 이미지", example = "user.jpg")
    private String imgUrl;

    @Schema(description = "유저 닉네임", example = "티끌모아 티끌")
    private String nickname;

    @Schema(description = "유저 레벨", example = "1")
    private int level;

    public List<ProfileResponse> toProfileResponses(List<UserChallenge> userChallenges) {
        return userChallenges.stream()
                .map(
                        userChallenge ->
                                new ProfileResponse(
                                        userChallenge.getImgUrl(),
                                        userChallenge.getNickname(),
                                        userChallenge.getUserLevel()))
                .collect(Collectors.toList());
    }
}
