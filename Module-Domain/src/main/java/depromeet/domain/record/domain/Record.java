package depromeet.domain.record.domain;


import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "challenge_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_record_id")
    private Long id;

    @Column(nullable = false)
    private Long challengeRoomId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int price;

    @Column(length = 20)
    private String name;

    @Column(length = 80)
    private String content;

    @Column private String imgUrl;

    @Enumerated(EnumType.STRING)
    private RecordEvaluation evaluation;

    /** 생성 메서드 */
    @Builder
    public Record(
            Long challengeRoomId,
            Long userId,
            int price,
            String name,
            String content,
            String imgUrl,
            RecordEvaluation evaluation) {
        this.challengeRoomId = challengeRoomId;
        this.userId = userId;
        this.price = price;
        this.name = name;
        this.content = content;
        this.imgUrl = imgUrl;
        this.evaluation = evaluation;
    }

    public static Record createRecord(
            Long challengeRoomId,
            Long userId,
            int price,
            String name,
            String content,
            String imgUrl,
            RecordEvaluation evaluation) {
        return Record.builder()
                .challengeRoomId(challengeRoomId)
                .userId(userId)
                .price(price)
                .name(name)
                .content(content)
                .imgUrl(imgUrl)
                .evaluation(evaluation)
                .build();
    }
}
