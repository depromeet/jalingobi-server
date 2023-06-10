package depromeet.domain.keyword.domain;


import depromeet.domain.keyword.exception.KeywordFormatException;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {

    @Id
    @Column(name = "keyword_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyword;

    public Keyword(String keyword) {
        if (isNotValidKeyword(keyword)) {
            throw KeywordFormatException.EXCEPTION;
        }
        this.keyword = keyword.toLowerCase();
    }

    private boolean isNotValidKeyword(String name) {
        return Objects.isNull(name) || name.isBlank();
    }
}
