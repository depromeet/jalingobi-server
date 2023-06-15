package depromeet.api.domain.comment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = CommentController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    @Autowired MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @MockBean CreateCommentUseCase createCommentUseCase;

    @Test
    @DisplayName("댓글 생성")
    public void createCommentTest() throws Exception {
        CreateCommentRequest request = CreateCommentRequest.builder().content("반가워요!").build();

        CreateCommentResponse response =
                CreateCommentResponse.builder()
                        .id(1L)
                        .imgUrl("test.jpg")
                        .nickname("test")
                        .content(request.getContent())
                        .createdAt(LocalDateTime.now())
                        .build();

        when(createCommentUseCase.execute(anyLong(), any())).thenReturn(response);

        mockMvc.perform(
                        post("/records/{recordId}/comments", 1L)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.id").value(response.getId()),
                        jsonPath("$.result.imgUrl").value(response.getImgUrl()),
                        jsonPath("$.result.nickname").value(response.getNickname()),
                        jsonPath("$.result.content").value(response.getContent()));

        verify(createCommentUseCase, times(1)).execute(anyLong(), any());
    }
}
