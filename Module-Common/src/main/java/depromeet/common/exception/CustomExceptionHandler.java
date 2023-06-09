package depromeet.common.exception;


import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errorMessages = new HashMap<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errorMessages.put(fieldName, errorMessage);
                        });

        return ResponseService.getExceptionResponse(
                CustomExceptionStatus.REQUEST_ERROR, errorMessages);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Response customException(CustomException customException) {
        CustomExceptionStatus status = customException.getCustomExceptionStatus();
        return ResponseService.getExceptionResponse(status);
    }
}
