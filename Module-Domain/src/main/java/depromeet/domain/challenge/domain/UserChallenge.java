package depromeet.domain.challenge.domain;


import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.record.domain.Record;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.Emojis;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Embedded private Emojis emojis;

    private String nickname;

    @Column(name = "current_charge")
    private int currentCharge;

    public void reactEmoji(Record record, String type) {
        Emoji emoji = Emoji.createEmoji(this, record, type);
        emojis.add(emoji);
    }

    public int getEmojiCounts() {
        return emojis.getCounts();
    }
}
