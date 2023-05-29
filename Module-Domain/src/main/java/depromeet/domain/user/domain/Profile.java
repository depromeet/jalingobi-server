package depromeet.domain.user.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 320, nullable = false)
    private String email;

    private String imageUrl;
}
