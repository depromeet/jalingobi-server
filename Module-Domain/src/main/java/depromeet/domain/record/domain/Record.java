package depromeet.domain.record.domain;

import static javax.persistence.FetchType.LAZY;

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

    @Column(nullable = false)
    private Long challengeRoomId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int price;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 80, nullable = false)
    private String content;

    @Column private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordEvaluation evaluation;

    /** 생성 메서드 */
    public static Record createRecord(
            Long challengeRoomId,
            User user,
            int price,
            String name,
            String content,
            String imgUrl,
            RecordEvaluation evaluation) {
        return Record.builder()
                .challengeRoomId(challengeRoomId)
                .user(user)
                .price(price)
                .name(name)
                .content(content)
                .imgUrl(imgUrl)
                .evaluation(evaluation)
                .build();
    }
}
