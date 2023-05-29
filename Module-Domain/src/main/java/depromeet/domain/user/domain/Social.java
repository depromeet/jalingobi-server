package depromeet.domain.user.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Social {

    @Column(name = "social_id", nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Platform platform;
}
