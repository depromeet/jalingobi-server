package depromeet.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import depromeet.api.HelloController;
import depromeet.api.util.JwtUtil;
import depromeet.common.response.ResponseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {HelloController.class})
@Import(HelloController.class)
@DisplayName("[Controller] Health check")
class HelloControllerTest {

    @MockBean private JwtUtil jwtUtil;

    @MockBean private ResponseService responseService;
    @Autowired MockMvc mvc;

    @Test
    @WithMockUser
    @DisplayName("[GET] Health check test")
    void testHealthCheckTest() throws Exception {
        mvc.perform(
                        get("/").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }
}
