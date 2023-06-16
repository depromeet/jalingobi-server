package depromeet.domain.jalingobi.domain;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "small_talk")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmallTalk {
    @Id
    @Column(name = "small_talk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jalingobi_id")
    private Jalingobi jalingobi;

    @Column(nullable = false)
    private String content;
}
