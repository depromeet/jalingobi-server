package depromeet.api.domain.image.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.image.dto.request.GetPresignedUrlRequest;
import depromeet.api.domain.image.dto.response.GetPresignedUrlResponse;
import depromeet.api.domain.image.usecase.GetPresignedUrlUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/image")
@RequiredArgsConstructor
public class ImageController {

    private final GetPresignedUrlUseCase getPresignedUrlUseCase;
    private final ResponseService responseService;

    @GetMapping("/presigned")
    public Response<GetPresignedUrlResponse> createPresigned(
            @RequestParam GetPresignedUrlRequest imageFileExtension) {
        return responseService.getDataResponse(
                getPresignedUrlUseCase.execute(
                        getCurrentUserSocialId(), imageFileExtension.getImageFileExtension()));
    }
}
