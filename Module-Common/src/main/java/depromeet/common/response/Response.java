package depromeet.common.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    protected Boolean isSuccess;

    protected int code;

    protected String message;

    private T result;
}
