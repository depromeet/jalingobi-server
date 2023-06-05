package depromeet.domain.rule.domain;


import depromeet.domain.challenge.domain.Challenge;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "challenge_rule")
public class ChallengeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_rule_id")
    private Long id;

    @Column(length = 18, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    public static ChallengeRule createRule(String content, Challenge challenge) {
        return ChallengeRule.builder().content(content).challenge(challenge).build();
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
