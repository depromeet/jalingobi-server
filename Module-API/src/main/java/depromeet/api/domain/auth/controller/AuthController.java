package depromeet.api.domain.auth.controller;


import depromeet.common.annotation.Adaptor;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Adaptor
public class AuthController {

    private final ResponseService responseService;

    @PostMapping("/auth")
    public CommonResponse auth() {

        return responseService.getSuccessResponse();
    }
}
