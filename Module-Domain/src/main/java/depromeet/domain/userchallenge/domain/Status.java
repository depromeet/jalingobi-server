package depromeet.domain.userchallenge.domain;


import depromeet.domain.userchallenge.exception.StatusNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PROCEEDING("참가중"),
    SUCCESS("성공"),
    FAILURE("실패");

    private final String value;

    public static Status of(String source) {
        return Arrays.stream(Status.values())
                .filter(value -> value.getValue().equals(source))
                .findAny()
                .orElseThrow(() -> StatusNotFoundException.EXCEPTION);
    }
}
