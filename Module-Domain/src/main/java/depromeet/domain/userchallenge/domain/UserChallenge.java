package depromeet.domain.userchallenge.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.config.BaseTime;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import java.util.ArrayList;
import java.util.List;
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
    @Column(name = "user_challenge_id")
    private Long id;

    private String nickname;

    @Column(name = "current_charge", nullable = false)
    private Integer currentCharge;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @OneToMany(mappedBy = "userChallenge", cascade = CascadeType.REMOVE)
    private List<Record> records = new ArrayList<>();

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

    public int getUserLevel() {
        return Level.getEnumTypeByScore(this.getUser().getScore()).getScore();
    }
}
