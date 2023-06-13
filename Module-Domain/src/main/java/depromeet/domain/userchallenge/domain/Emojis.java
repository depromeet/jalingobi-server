package depromeet.domain.userchallenge.domain;


import depromeet.domain.emoji.domain.Emoji;
import depromeet.domain.emoji.exception.DuplicatedEmojiException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class Emojis {
    @OneToMany(
            mappedBy = "userChallenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<Emoji> emojis;

    public Emojis() {
        this(new ArrayList<>());
    }

    public Emojis(List<Emoji> emojis) {
        this.emojis = emojis;
    }

    public int getCounts() {
        return emojis.size();
    }

    public void add(Emoji emoji) {
        if (emojis.contains(emoji)) {
            throw DuplicatedEmojiException.EXCEPTION;
        }
        emojis.add(emoji);
    }
}
