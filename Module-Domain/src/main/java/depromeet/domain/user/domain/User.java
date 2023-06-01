package depromeet.domain.user.domain;


import depromeet.domain.config.BaseTime;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded private Profile profile;

    @Embedded private Social social;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role;

    public static User createUser(
            String nickname, String email, String socialId, Platform platform) {

        Profile profile = Profile.createProfile(nickname, email, null);
        Social social = Social.createSocial(socialId, platform);
        return User.builder().profile(profile).social(social).role(Role.USER).build();
    }

    public static User registerUser(
            String nickname, String email, String socialId, Platform platform) {

        Profile profile = Profile.createProfile(nickname, email, null);
        Social social = Social.createSocial(socialId, platform);
        return User.builder().profile(profile).social(social).role(Role.USER).build();
    }
}
