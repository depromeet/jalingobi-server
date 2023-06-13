package depromeet.domain.userchallenge.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.config.BaseTime;
import depromeet.domain.user.domain.User;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UniqueUserChallenge",
                    columnNames = {"user_id", "challenge_id"})
        })
public class UserChallenge extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    private String nickname;

    @Column(name = "current_charge")
    private Integer currentCharge;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public static UserChallenge createUserChallenge(
            User user, Challenge challenge, String nickname, int currentCharge) {
        return UserChallenge.builder()
                .user(user)
                .challenge(challenge)
                .nickname(nickname)
                .currentCharge(currentCharge)
                .status(Status.PROCEEDING)
                .build();
    }
}
