package depromeet.api.domain.user.dto.response;


import depromeet.domain.user.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetUserInfoResponse {
    private Long id;
    private String nickname;
    private String email;
    private String imgUrl;
    private String platform;
    private String role;
    private Integer score;

    public static GetUserInfoResponse createGetUserInfoResponse(User user) {
        Profile profile = user.getProfile();
        Social social = user.getSocial();
        return GetUserInfoResponse.builder()
                .id(user.getId())
                .nickname(profile.getNickname())
                .email(profile.getEmail())
                .imgUrl(profile.getImgUrl())
                .platform(social.getPlatform().toString())
                .role(user.getRole().getAuthority())
                .score(user.getScore())
                .build();
    }
}
