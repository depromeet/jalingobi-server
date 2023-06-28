package depromeet.domain.user.domain;


import depromeet.domain.user.util.ImageUrlUtil;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.*;

@Builder
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 320, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String imgUrl;

    /** 생성 메서드 */
    public static Profile createProfile(String name, String email) {
        String imgUrl = ImageUrlUtil.defaultImgUrl;
        return Profile.builder().nickname(name).email(email).imgUrl(imgUrl).build();
    }

    /** 비즈니스 메서드 */
    public void updateProfile(String nickname, String profileImgUrl) {
        this.nickname = nickname;
        this.imgUrl = profileImgUrl;
    }

    public void withdrawal() {
        this.nickname = "[탈퇴 유저] 집나간 자린고비";
        this.email = "";
        this.imgUrl = "";
    }
}
