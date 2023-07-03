package depromeet.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
}
