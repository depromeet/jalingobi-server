package depromeet.domain.user.domain;

public enum Role {
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
