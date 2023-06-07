package depromeet.domain.challenge.domain;


import depromeet.domain.category.domain.Category;
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

    @Embedded private ChallengeCategories challengeCategories;

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

    @OneToMany(mappedBy = "challenge")
    private List<UserChallenge> userChallenges;

    @Column(name = "challenge_rule")
    @OneToMany(
            mappedBy = "challenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<ChallengeRule> challengeRules;

    @Embedded private Duration duration;

    /** 생성 메서드 */
    public static Challenge createChallenge(
            String title,
            int price,
            String imgUrl,
            String hashtag,
            int availableCount,
            String createdBy,
            List<ChallengeRule> challengeRules,
            ChallengeCategories challengeCategories,
            Duration duration) {
        return Challenge.builder()
                .title(title)
                .price(price)
                .imgUrl(imgUrl)
                .hashtag(hashtag)
                .availableCount(availableCount)
                .createdBy(createdBy)
                .challengeRules(challengeRules)
                .challengeCategories(challengeCategories)
                .duration(duration)
                .build();
    }

    /** 비즈니스 메서드 */
    public void addRules(List<ChallengeRule> rules) {
        challengeRules.addAll(rules);
        rules.forEach(rule -> rule.setChallenge(this));
    }

    public void addCategories(List<Category> categories) {
        challengeCategories.addAll(this, categories);
    }

    public boolean isParticipateChallengeUser(String socialId) {
        return this.userChallenges.stream()
                .anyMatch(
                        userChallenge ->
                                userChallenge.getUser().getSocial().getId().equals(socialId));
    }
}
