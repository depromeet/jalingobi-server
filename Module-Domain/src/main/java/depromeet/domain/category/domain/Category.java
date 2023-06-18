package depromeet.domain.category.domain;


import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column private String name;

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category toDomain() {
        return new Category(id, name);
    }
}
