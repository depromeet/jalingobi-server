package depromeet.api.domain.comment.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import depromeet.api.domain.comment.usecase.DeleteCommentUseCase;
import depromeet.api.domain.comment.usecase.UpdateCommentUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUsecase;

    @PostMapping("/{recordId}/comment")
    public Response<CreateCommentResponse> createComment(
            @PathVariable long recordId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseService.getDataResponse(createCommentUseCase.execute(recordId, request));
    }

    @PutMapping("/{recordId}/comment/{commentId}")
    public CommonResponse updateComment(
            @PathVariable long recordId,
            @PathVariable long commentId,
            @RequestBody @Valid UpdateCommentRequest request) {
        updateCommentUseCase.execute(request, getCurrentUserSocialId(), commentId);
        return ResponseService.getSuccessResponse();
    }

    @DeleteMapping("/{recordId}/comment/{commentId}")
    public CommonResponse deleteComment(@PathVariable long recordId, @PathVariable long commentId) {
        deleteCommentUsecase.execute(getCurrentUserSocialId(), commentId);
        return ResponseService.getSuccessResponse();
    }
}
