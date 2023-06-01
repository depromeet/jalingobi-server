package depromeet.domain.rule.domain;


import depromeet.domain.challenge.domain.Challenge;
import javax.persistence.*;

@Entity
@Table(name = "challenge_rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_rule_id")
    private Long id;

    @Column(length = 18, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;
}
