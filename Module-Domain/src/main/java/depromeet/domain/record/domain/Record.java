package depromeet.domain.record.domain;


import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.comment.domain.Comment;
import depromeet.domain.config.BaseTime;
import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.ArrayList;
import java.util.List;
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
    private int price;

    @Column(length = 16, nullable = false)
    private String title;

    @Column(length = 80, nullable = false)
    private String content;

    @Column private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Evaluation evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_challenge_id")
    private UserChallenge userChallenge;

    @OneToMany(mappedBy = "record", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(
            mappedBy = "record",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Emoji> emojis = new ArrayList<>();

    /** 생성 메서드 */
    public static Record createRecord(
            Challenge challenge,
            User user,
            UserChallenge userChallenge,
            int price,
            String title,
            String content,
            String imgUrl,
            Evaluation evaluation) {
        return Record.builder()
                .challenge(challenge)
                .user(user)
                .userChallenge(userChallenge)
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
        emojis.clear();
        Emoji emoji = Emoji.createEmoji(userChallenge, this, type);
        emojis.add(emoji);
    }

    public void unReactEmoji(UserChallenge userChallenge, String type) {
        Emoji emoji = Emoji.createEmoji(userChallenge, this, type);
        emojis.remove(emoji);
    }

    public int getEmojiCounts() {
        return emojis.size();
    }
}
