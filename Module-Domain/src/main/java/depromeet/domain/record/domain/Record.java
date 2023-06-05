package depromeet.domain.record.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.config.BaseTime;
import depromeet.domain.user.domain.User;
import javax.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "challenge_record")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int price;

    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 80, nullable = false)
    private String content;

    @Column private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordEvaluation evaluation;

    /** 생성 메서드 */
    public static Record createRecord(
            Challenge challenge,
            User user,
            int price,
            String name,
            String content,
            String imgUrl,
            RecordEvaluation evaluation) {
        return Record.builder()
                .challenge(challenge)
                .user(user)
                .price(price)
                .name(name)
                .content(content)
                .imgUrl(imgUrl)
                .evaluation(evaluation)
                .build();
    }
}
