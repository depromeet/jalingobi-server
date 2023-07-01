package depromeet.api.domain.image.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.image.dto.request.IssuePresignedUrlRequest;
import depromeet.api.domain.image.dto.response.IssuePresignedUrlResponse;
import depromeet.api.domain.image.usecase.IssuePresignedUrlUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "이미지 업로드", description = "presigned url API")
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final IssuePresignedUrlUseCase getPresignedUrlUseCase;

    // type은 [record, profile, custom-profile, challenge] 이게 폴더 명이 될거임.
    @PostMapping("/presigned")
    @Operation(summary = "presigned url을 발급받는 API")
    public Response<IssuePresignedUrlResponse> createPresigned(
            @RequestBody IssuePresignedUrlRequest issuePresignedUrlRequest) {

        return ResponseService.getDataResponse(
                getPresignedUrlUseCase.execute(getCurrentUserSocialId(), issuePresignedUrlRequest));
    }
}
