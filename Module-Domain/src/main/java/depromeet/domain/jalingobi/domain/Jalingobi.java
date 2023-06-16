package depromeet.domain.jalingobi.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Jalingobi {
    @Id
    @Column(name = "jalingobi_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String acquisitionCondition;

    @OneToMany(mappedBy = "jalingobi", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<SmallTalk> smallTalks = new ArrayList<>();
}
