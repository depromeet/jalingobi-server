package depromeet.api;


import depromeet.api.util.JwtUtil;
import depromeet.common.response.ResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ModuleApiApplication.class})
@Import(HelloController.class)
class ModuleApiApplicationTests {
    @MockBean private JwtUtil jwtUtil;

    @MockBean private ResponseService responseService;
    @Autowired MockMvc mvc;

    @Test
    void contextLoads() {}
}
