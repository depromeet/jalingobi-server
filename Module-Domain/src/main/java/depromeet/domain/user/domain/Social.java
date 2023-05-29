package depromeet.domain.user.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.*;

@Builder
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Social {

    @Column(name = "social_id", unique = true, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Platform platform;

    public static Social createSocial(String socialId, Platform platform) {
        return Social.builder().id(socialId).platform(platform).build();
    }
}
