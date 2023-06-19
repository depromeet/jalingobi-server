package depromeet.domain.user.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    NORMAL("NORMAL"),
    DELETED("DELETED"),
    FORBIDDEN("FORBIDDEN");

    private String value;
}
