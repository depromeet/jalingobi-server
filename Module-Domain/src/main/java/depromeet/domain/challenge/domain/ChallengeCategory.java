package depromeet.domain.challenge.domain;


import depromeet.domain.category.domain.Category;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeCategory {

    @Id
    @Column(name = "challenge_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private depromeet.domain.category.domain.Category category;

    public ChallengeCategory(
            Challenge challenge, depromeet.domain.category.domain.Category category) {
        this.challenge = challenge;
        this.category = category;
    }

    public boolean hasSameCategory(Category category) {
        return Objects.equals(this.category, category);
    }
}
