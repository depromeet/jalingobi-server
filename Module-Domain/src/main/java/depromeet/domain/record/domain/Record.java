package depromeet.domain.record.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.comment.domain.Comments;
import depromeet.domain.config.BaseTime;
import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.Emojis;
import depromeet.domain.userchallenge.domain.UserChallenge;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_challenge_id")
    private UserChallenge userChallenge;

    @Embedded private Comments comments;

    @Embedded private Emojis emojis;

    @Column(nullable = false)
    private int price;

    @Column(length = 16, nullable = false)
    private String title;

    @Column(length = 80, nullable = false)
    private String content;

    @Column private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Evaluation evaluation;

    /** 생성 메서드 */
    public static Record createRecord(
            Challenge challenge,
            User user,
            int price,
            String title,
            String content,
            String imgUrl,
            Evaluation evaluation) {
        return Record.builder()
                .challenge(challenge)
                .user(user)
                .price(price)
                .title(title)
                .content(content)
                .imgUrl(imgUrl)
                .evaluation(evaluation)
                .build();
    }

    /** 비즈니스 메서드 */
    public void updateRecord(
            int price, String title, String content, String imgUrl, int evaluation) {
        this.price = price;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.evaluation = Evaluation.getEnumTypeByValue(evaluation);
    }

    public void reactEmoji(UserChallenge userChallenge, String type) {
        depromeet.domain.emoji.domain.Emoji emoji = Emoji.createEmoji(userChallenge, this, type);
        emojis.add(emoji);
    }

    public int getEmojiCounts() {
        return emojis.getCounts();
    }
}
