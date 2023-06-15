package depromeet.domain.comment.domain;


import depromeet.domain.config.BaseTime;
import depromeet.domain.record.domain.Record;
import depromeet.domain.userchallenge.domain.UserChallenge;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_challenge_id")
    private UserChallenge userChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_record_id")
    private Record record;

    private String content;

    @Builder
    public Comment(UserChallenge userChallenge, Record record, String content) {
        this.userChallenge = userChallenge;
        this.record = record;
        this.content = content;
    }

    public static Comment create(UserChallenge userChallenge, Record record, String content) {
        return new Comment(userChallenge, record, content);
    }

    public String getUserChallengeImageUrl() {
        return userChallenge.getImgUrl();
    }

    public String getUserChallengeNickName() {
        return userChallenge.getNickname();
    }
}
