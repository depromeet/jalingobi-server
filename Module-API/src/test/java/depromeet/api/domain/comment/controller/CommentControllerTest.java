package depromeet.api.domain.comment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.dto.response.DeleteCommentResponse;
import depromeet.api.domain.comment.dto.response.UpdateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import depromeet.api.domain.comment.usecase.DeleteCommentUseCase;
import depromeet.api.domain.comment.usecase.UpdateCommentUseCase;
import depromeet.api.util.AuthenticationUtil;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
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

    @MockBean UpdateCommentUseCase updateCommentUseCase;

    @MockBean DeleteCommentUseCase deleteCommentUseCase;

    private static MockedStatic<AuthenticationUtil> authenticationUtil;

    @BeforeAll
    public static void beforeALl() {
        authenticationUtil = mockStatic(AuthenticationUtil.class);
    }

    @AfterAll
    public static void afterAll() {
        authenticationUtil.close();
    }

    String socialId = "socialId";

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
                        post("/record/{recordId}/comment", 1L)
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

    @Test
    @DisplayName("댓글 수정")
    public void updateCommentTest() throws Exception {
        UpdateCommentRequest request = UpdateCommentRequest.builder().content("반가워요!").build();
        long commentId = 1L;

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(updateCommentUseCase.execute(any(), anyString(), anyLong()))
                .thenReturn(UpdateCommentResponse.builder().commentId(commentId).build());

        mockMvc.perform(
                        put("/record/{recordId}/comment/{commentId}", 1L, commentId)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpectAll(status().isOk(), jsonPath("$.result.commentId").value(commentId));

        verify(updateCommentUseCase, times(1)).execute(any(), anyString(), anyLong());
    }

    @Test
    @DisplayName("댓글 삭제")
    public void deleteCommentTest() throws Exception {
        long commentId = 1L;

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(deleteCommentUseCase.execute(AuthenticationUtil.getCurrentUserSocialId(), commentId))
                .thenReturn(DeleteCommentResponse.builder().commentId(commentId).build());

        mockMvc.perform(delete("/record/{recordId}/comment/{commentId}", 1L, commentId))
                .andDo(print())
                .andExpectAll(status().isOk(), jsonPath("$.result.commentId").value(commentId));

        verify(deleteCommentUseCase, times(1)).execute(anyString(), anyLong());
    }
}
