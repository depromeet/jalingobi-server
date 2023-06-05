package depromeet.domain.user.domain;

public enum Role {
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
