package depromeet.domain.challenge.domain;


import depromeet.domain.config.BaseTime;
import java.util.Date;
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

    private String hashtag;

    @Column(name = "available_count", nullable = false)
    private int availableCount;

    @Column(nullable = false)
    private int period;

    @Column(name = "start_at", nullable = false)
    private Date startAt;

    @Column(name = "end_at", nullable = false)
    private Date endAt;

    @Builder
    public Challenge(
            Category category,
            String title,
            String imgUrl,
            String hashtag,
            int availableCount,
            Date startAt,
            Date endAt) {
        this.category = category;
        this.title = title;
        this.imgUrl = imgUrl;
        this.hashtag = hashtag;
        this.availableCount = availableCount;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
