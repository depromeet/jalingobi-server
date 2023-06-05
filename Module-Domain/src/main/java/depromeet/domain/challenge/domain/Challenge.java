package depromeet.domain.challenge.domain;


import depromeet.domain.config.BaseTime;
import depromeet.domain.rule.domain.ChallengeRule;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    private String imgUrl;

    @Column(nullable = false)
    private String hashtag;

    @Column(name = "available_count", nullable = false)
    private int availableCount;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "challenge_rule")
    @OneToMany(
            mappedBy = "challenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<ChallengeRule> challengeRules;

    @Embedded private Duration duration;

    public static Challenge createChallenge(
            Category category,
            String title,
            int price,
            String imgUrl,
            String hashtag,
            int availableCount,
            String createdBy,
            List<ChallengeRule> challengeRules,
            Duration duration) {
        return Challenge.builder()
                .category(category)
                .title(title)
                .price(price)
                .imgUrl(imgUrl)
                .hashtag(hashtag)
                .availableCount(availableCount)
                .createdBy(createdBy)
                .challengeRules(challengeRules)
                .duration(duration)
                .build();
    }

    public void addRules(List<ChallengeRule> rules) {
        challengeRules.addAll(rules);
        rules.forEach(rule -> rule.setChallenge(this));
    }
}
