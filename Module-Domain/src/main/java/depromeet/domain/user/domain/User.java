package depromeet.domain.user.domain;


import depromeet.domain.config.BaseTime;
import depromeet.domain.user.exception.AlreadyWithdrawalUserException;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role;

    @Builder.Default
    @ColumnDefault("false")
    private Boolean notification = false;

    @Column(nullable = false)
    private Integer score;

    @OneToMany(mappedBy = "user")
    private List<UserChallenge> userChallenges;

    /** 생성 메서드 */
    public static User registerUser(
            String nickname, String email, String socialId, Platform platform) {

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        return User.builder()
                .profile(profile)
                .social(social)
                .role(Role.USER)
                .status(Status.NORMAL)
                .score(1)
                .build();
    }

    /** 비즈니스 메서드 */
    public boolean isSameUser(String socialId) {
        return this.social.getId().equals(socialId);
    }

    public void updateProfile(String nickname, String profileImgUrl) {
        this.profile.updateProfile(nickname, profileImgUrl);
    }

    public void withdrawal() {
        if (status.equals(Status.DELETED)) {
            throw AlreadyWithdrawalUserException.EXCEPTION;
        }

        this.status = Status.DELETED;
        this.profile.withdrawal();
        this.notification = Boolean.FALSE;
        this.score = 0;
    }

    public String getProfileImgUrl() {
        return this.getProfile().getImgUrl();
    }

    public String getProfileNickname() {
        return this.getProfile().getNickname();
    }

    public void plusScore() {
        if (score < 6) score++;
    }

    public void minusScore() {
        if (score > 0) score--;
    }
}
