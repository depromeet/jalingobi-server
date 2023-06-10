package depromeet.api.domain.guest.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import depromeet.api.config.security.filter.JwtRequestFilter;
import depromeet.api.domain.guest.dto.ExperienceGuestModeResponse;
import depromeet.api.domain.guest.usecase.ExperienceGuestModeUseCase;
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
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(
        controllers = GuestController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {JwtRequestFilter.class})
        })
@AutoConfigureDataJpa
@AutoConfigureMockMvc(addFilters = false)
public class GuestControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ExperienceGuestModeUseCase experienceGuestModeUseCase;

    @Test
    public void experienceGuestMode_성공() throws Exception {
        // given
        ExperienceGuestModeResponse experienceGuestModeResponse =
                new ExperienceGuestModeResponse("guestToken");
        given(experienceGuestModeUseCase.execute()).willReturn(experienceGuestModeResponse);

        // when
        ResultActions actions =
                mockMvc.perform(get("/guest").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result.accessToken")
                                .value(experienceGuestModeResponse.getAccessToken()));
    }
}
