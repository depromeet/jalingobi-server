package depromeet.domain.challenge.domain.keyword;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.keyword.domain.Keyword;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeKeyword {

    @Id
    @Column(name = "challenge_keyword_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    public boolean hasSameKeyword(Keyword keyword) {
        return Objects.equals(this.keyword, keyword);
    }
}
