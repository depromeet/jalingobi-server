package depromeet.api.domain.comment.controller;


import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping("/records/{recordId}/comments")
    public Response<CreateCommentResponse> createComment(
            @PathVariable Long recordId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseService.getDataResponse(createCommentUseCase.execute(recordId, request));
    }
}
