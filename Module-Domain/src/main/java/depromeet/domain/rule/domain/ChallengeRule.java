package depromeet.domain.rule.domain;


import depromeet.domain.challenge.domain.Challenge;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@Table(name = "challenge_rule")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public ChallengeRule(String content) {
        this.content = content;
    }

    public static ChallengeRule createRule(String content, Challenge challenge) {
        return ChallengeRule.builder().content(content).challenge(challenge).build();
    }

    public static List<ChallengeRule> toEntities(List<String> rules, Challenge challenge) {
        return rules.stream().map(rule -> createRule(rule, challenge)).collect(Collectors.toList());
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
