package depromeet.api.domain.image.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.image.dto.request.IssuePresignedUrlRequest;
import depromeet.api.domain.image.dto.response.IssuePresignedUrlResponse;
import depromeet.api.domain.image.usecase.IssuePresignedUrlUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final IssuePresignedUrlUseCase getPresignedUrlUseCase;
    private final ResponseService responseService;

    // type은 [record, profile, challenge] 이게 폴더 명이 될거임.
    @PostMapping("/presigned")
    public Response<IssuePresignedUrlResponse> createPresigned(
            @RequestBody IssuePresignedUrlRequest getPresignedUrlRequest) {
        return responseService.getDataResponse(
                getPresignedUrlUseCase.execute(
                        getCurrentUserSocialId(),
                        getPresignedUrlRequest.getImageFileExtension(),
                        getPresignedUrlRequest.getType()));
    }
}
