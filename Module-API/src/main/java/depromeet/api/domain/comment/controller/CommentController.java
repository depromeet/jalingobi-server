package depromeet.api.domain.comment.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import depromeet.api.domain.comment.usecase.UpdateCommentUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;

    @PostMapping("/record/{recordId}/comment")
    public Response<CreateCommentResponse> createComment(
            @PathVariable Long recordId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseService.getDataResponse(createCommentUseCase.execute(recordId, request));
    }

    @PutMapping("/record/{recordId}/comment/{commentId}")
    public CommonResponse updateComment(
            @PathVariable Long recordId,
            @PathVariable Long commentId,
            @RequestBody @Valid UpdateCommentRequest request) {
        updateCommentUseCase.execute(request, getCurrentUserSocialId(), commentId);
        return ResponseService.getSuccessResponse();
    }
}
