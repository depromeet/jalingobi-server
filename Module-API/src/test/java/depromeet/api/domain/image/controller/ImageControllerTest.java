package depromeet.api.domain.image.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.image.dto.ImageFileExtension;
import depromeet.api.domain.image.dto.ImageUploadType;
import depromeet.api.domain.image.dto.request.IssuePresignedUrlRequest;
import depromeet.api.domain.image.dto.response.IssuePresignedUrlResponse;
import depromeet.api.domain.image.usecase.IssuePresignedUrlUseCase;
import depromeet.api.util.AuthenticationUtil;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
        controllers = ImageController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class ImageControllerTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private IssuePresignedUrlUseCase issuePresignedUrlUseCase;

    private static MockedStatic<AuthenticationUtil> authenticationUtil;

    @BeforeAll
    public static void beforeAll() {
        authenticationUtil = mockStatic(AuthenticationUtil.class);
    }

    @AfterAll
    public static void afterAll() {
        authenticationUtil.close();
    }

    @Test
    @DisplayName("[POST] presigned url 발급")
    public void ImageControllerTest() throws Exception {
        // given
        IssuePresignedUrlRequest issuePresignedUrlRequest =
                IssuePresignedUrlRequest.builder()
                        .imageFileExtension(ImageFileExtension.JPG)
                        .type(ImageUploadType.PROFILE)
                        .build();

        IssuePresignedUrlResponse issuePresignedUrlResponse =
                IssuePresignedUrlResponse.builder()
                        .presignedUrl("dasfgsdfadagdsafgdsagsd")
                        .key("profile/userid/dahjfhlfa.jpg")
                        .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/image/presigned")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(issuePresignedUrlRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        String socialId = "socialId";
        given(AuthenticationUtil.getCurrentUserSocialId()).willReturn(socialId);
        given(issuePresignedUrlUseCase.execute(anyString(), any(IssuePresignedUrlRequest.class)))
                .willReturn(issuePresignedUrlResponse);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.presignedUrl")
                                .value(issuePresignedUrlResponse.getPresignedUrl()),
                        jsonPath("$.result.key").value(issuePresignedUrlResponse.getKey()));
    }
}
