package depromeet.domain.emoji.domain;


import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emoji_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_challenge_id", nullable = false)
    private UserChallenge userChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_record_id", nullable = false)
    private Record record;

    @Enumerated(EnumType.STRING)
    private EmojiType type;

    public static Emoji createEmoji(UserChallenge userchallenge, Record record, String type) {
        return Emoji.builder()
                .userChallenge(userchallenge)
                .record(record)
                .type(EmojiType.of(type))
                .build();
    }

    public String getType() {
        return type.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emoji emoji = (Emoji) o;
        return Objects.equals(userChallenge, emoji.getUserChallenge())
                && Objects.equals(record, emoji.getRecord())
                && Objects.equals(type, emoji.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userChallenge, record, type);
    }
}
