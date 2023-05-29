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

    private String imageUrl;

    public static Profile createProfile(String name, String email, String imageUrl) {
        return Profile.builder().name(name).email(email).imageUrl(imageUrl).build();
    }
}
