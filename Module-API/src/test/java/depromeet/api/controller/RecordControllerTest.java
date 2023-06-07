package depromeet.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.record.controller.RecordController;
import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.usecase.CreateRecordUseCase;
import depromeet.api.domain.record.usecase.GetRecordUseCase;
import depromeet.api.util.AuthenticationUtil;
import java.util.Objects;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.NullAndEmptySource;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @Mock은 Mockito를 사용하여 순수 Java 코드 단위 테스트에 목 객체를 생성하는 데 사용되고, @MockBean은 Spring Boot의 스프링 통합 테스트에서
 * 스프링 컨텍스트와 상호작용하는 가짜 빈을 생성하는 데 사용
 */
@WebMvcTest(
        controllers = RecordController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
class RecordControllerTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private CreateRecordUseCase createRecordUseCase;

    @MockBean private GetRecordUseCase getRecordUseCase;

    @Autowired private ObjectMapper objectMapper;

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
    @DisplayName("[POST] 챌린지 지출 기록")
    public void CreateRecordTest() throws Exception {
        // given
        CreateRecordRequest createRecordRequest =
                CreateRecordRequest.builder()
                        .price(3000)
                        .name("커피")
                        .content("커피는 무죄야")
                        .imgUrl("")
                        .evaluation(1)
                        .build();

        CreateRecordResponse createRecordResponse =
                CreateRecordResponse.builder()
                        .id(1L)
                        .name("커피")
                        .content("커피는 무죄야")
                        .imgUrl("")
                        .evaluation(1)
                        .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/challenge/{challengeRoomId}/create", 1)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createRecordRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        String socialId = "socialId";

        when(AuthenticationUtil.getCurrentUserSocialId()).thenReturn(socialId);
        when(createRecordUseCase.execute(anyLong(), anyString(), any(CreateRecordRequest.class)))
                .thenReturn(createRecordResponse);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.result.id").value(createRecordResponse.getId()),
                        jsonPath("$.result.imgUrl").value(createRecordResponse.getImgUrl()),
                        jsonPath("$.result.evaluation")
                                .value(createRecordResponse.getEvaluation()));
    }

    @DisplayName("[예외 테스트] name null 예외 발생")
    @Test
    @NullAndEmptySource
    void createWithNullAndEmptyNameTest() throws Exception {
        CreateRecordRequest createRecordRequest =
                CreateRecordRequest.builder()
                        .price(3000)
                        .name(" ")
                        .content("커피는 무죄야")
                        .imgUrl("")
                        .evaluation(1)
                        .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/challenge/{challengeRoomId}/create", 1)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createRecordRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(
                        result -> {
                            Exception exception = result.getResolvedException();
                            Objects.requireNonNull(exception);
                            assertTrue(
                                    exception
                                            .getClass()
                                            .isAssignableFrom(
                                                    MethodArgumentNotValidException.class));
                        });
    }
}
