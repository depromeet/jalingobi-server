package depromeet.domain.user.domain;


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
    private String name;

    @Column(length = 320, unique = true, nullable = false)
    private String email;

    private String imgUrl;

    public static Profile createProfile(String name, String email, String imgUrl) {
        return Profile.builder().name(name).email(email).imgUrl(imgUrl).build();
    }

    public void updateProfile(String nickname, String profileImgUrl) {
        this.name = nickname;
        this.imgUrl = profileImgUrl;
    }
}
