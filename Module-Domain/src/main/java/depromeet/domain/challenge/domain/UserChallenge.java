package depromeet.domain.challenge.domain;


import depromeet.domain.user.domain.User;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UserChallenge {

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
    private int currentCharge;

    protected UserChallenge() {}

    public UserChallenge(User user, Challenge challenge) {
        this.user = user;
        this.challenge = challenge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserChallenge userChallenge = (UserChallenge) o;
        return Objects.equals(id, userChallenge.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
