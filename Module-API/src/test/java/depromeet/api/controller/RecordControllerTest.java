// package depromeet.api.controller;
//
// import static org.mockito.ArgumentMatchers.*;
// import static
// org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import depromeet.api.domain.record.controller.RecordController;
// import depromeet.api.domain.record.dto.request.CreateRecordRequest;
// import depromeet.api.domain.record.dto.response.CreateRecordResponse;
// import depromeet.api.domain.record.usecase.CreateRecordUseCase;
// import depromeet.api.util.JwtUtil;
// import depromeet.common.response.ResponseService;
// import depromeet.domain.record.domain.RecordEvaluation;
// import depromeet.domain.user.domain.Social;
// import depromeet.domain.user.domain.User;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.test.context.support.WithUserDetails;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
/// **
// * @Mock은 Mockito를 사용하여 순수 Java 코드 단위 테스트에 목 객체를 생성하는 데 사용되고, @MockBean은 Spring Boot의 스프링 통합 테스트에서
// * 스프링 컨텍스트와 상호작용하는 가짜 빈을 생성하는 데 사용
// */
// @WebMvcTest(controllers = {RecordController.class})
// @Import(RecordController.class)
// @DisplayName("Record Controller Test")
// class RecordControllerTest {
//    @Autowired private MockMvc mockMvc;
//    @MockBean private JwtUtil jwtUtil;
//
//    @MockBean private CreateRecordUseCase createRecordUseCase;
//
//    @MockBean private ResponseService responseService;
//
//    @Autowired private ObjectMapper objectMapper;
//
//    @Test
////    @WithUserDetails
//    @DisplayName("[POST] ")
//    public void CreateRecordTest() throws Exception {
//        // given
//        CreateRecordRequest createRecordRequest =
//                CreateRecordRequest.builder()
//                        .price(3000)
//                        .name("커피")
//                        .content("커피는 무죄야")
//                        .imgUrl("")
//                        .evaluation(1)
//                        .build();
//
//        CreateRecordResponse createRecordResponse =
//                CreateRecordResponse.builder()
//                        .id(1L)
//                        .name("커피")
//                        .content("커피는 무죄야")
//                        .imgUrl("")
//                        .evaluation(RecordEvaluation.ONE)
//                        .build();
//
//        MockHttpServletRequestBuilder requestBuilder =
//                MockMvcRequestBuilders.post("/challenge/{challengeRoomId}/create", 1)
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsString(createRecordRequest))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON);
//
//        //            when(createRecordUseCase.execute(anyLong(), anyString(),
//        // any(CreateRecordRequest.class))).thenReturn(createRecordResponse);
//
//        mockMvc.perform(requestBuilder).andExpectAll(status().isOk()).andDo(print());
//    }
//    //    @Test
//    //    @WithMockUser
//    //    public void testCreateRecord_InvalidRequestBody() throws Exception {
//    //        // Given
//    //        CreateRecordRequest createRecordRequest =
//    //                                CreateRecordRequest.builder()
//    //                                        .price(3000)
//    //                                        .name("커피")
//    //                                        .content("커피는 무죄야")
//    //                                        .imgUrl("")
//    //                                        .evaluation(2)
//    //                                        .build();
//    //        // When, Then
//    //        mockMvc.perform(MockMvcRequestBuilders.post("/challenge/{challengeRoomId}/create",
//    //                        .contentType(MediaType.APPLICATION_JSON)
//    //                        .content(objectMapper.writeValueAsString(createRecordRequest)))
//    //                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//    //                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("name can not
//    // be null"));
//    //    }
// }
