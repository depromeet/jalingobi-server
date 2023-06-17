package depromeet.common.response;


import depromeet.common.exception.CustomExceptionStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public static CommonResponse getSuccessResponse() {
        CommonResponse response = new CommonResponse();
        response.setIsSuccess(CustomExceptionStatus.SUCCESS.isSuccess());
        response.setCode(CustomExceptionStatus.SUCCESS.getCode());
        response.setMessage(CustomExceptionStatus.SUCCESS.getMessage());
        return response;
    }

    public static <T> Response<T> getDataResponse(T data) {
        Response<T> response = new Response<>();
        response.setResult(data);
        response.setIsSuccess(true);
        response.setCode(1000);
        response.setMessage("요청에 성공하였습니다.");
        return response;
    }

    public static CommonResponse getExceptionResponse(CustomExceptionStatus status) {
        CommonResponse response = new CommonResponse();
        response.setIsSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        return response;
    }

    public static <T> Response<T> getExceptionResponse(
            CustomExceptionStatus status, T errorMessages) {
        Response<T> response = new Response<>();
        response.setIsSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        response.setResult(errorMessages);
        return response;
    }
}
