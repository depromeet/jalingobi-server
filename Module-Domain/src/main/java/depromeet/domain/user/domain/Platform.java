package depromeet.domain.user.domain;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Platform {
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    APPLE("APPLE"),
    GUEST("GUEST");

    private final String value;

    public String toString() {
        return value;
    }
}
