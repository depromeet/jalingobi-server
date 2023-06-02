package depromeet.domain.challenge.domain;


import depromeet.domain.config.BaseTime;
import depromeet.domain.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded private Duration duration;
}
