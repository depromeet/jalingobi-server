package depromeet.api.config.security.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.common.exception.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AccessDeniedException e)
            throws IOException, ServletException {
        log.info("Access Denied Handler");

        ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter out = httpServletResponse.getWriter();
        String jsonResponse =
                objectMapper.writeValueAsString(CustomExceptionStatus.ACCOUNT_ACCESS_DENIED);
        out.print(jsonResponse);
    }
}
