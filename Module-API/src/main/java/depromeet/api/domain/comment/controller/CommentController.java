package depromeet.api.domain.comment.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.comment.dto.request.CreateCommentRequest;
import depromeet.api.domain.comment.dto.request.UpdateCommentRequest;
import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.api.domain.comment.dto.response.DeleteCommentResponse;
import depromeet.api.domain.comment.dto.response.UpdateCommentResponse;
import depromeet.api.domain.comment.usecase.CreateCommentUseCase;
import depromeet.api.domain.comment.usecase.DeleteCommentUseCase;
import depromeet.api.domain.comment.usecase.UpdateCommentUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
@Tag(name = "댓글", description = "댓글 CUD API")
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUsecase;

    @Operation(summary = "댓글 생성 API", description = "댓글을 생성합니다.")
    @PostMapping("/{recordId}/comment")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<CreateCommentResponse> createComment(
            @PathVariable long recordId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseService.getDataResponse(createCommentUseCase.execute(recordId, request));
    }

    @Operation(summary = "댓글 수정 API", description = "댓글을 수정합니다.")
    @PutMapping("/{recordId}/comment/{commentId}")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<UpdateCommentResponse> updateComment(
            @PathVariable long recordId,
            @PathVariable long commentId,
            @RequestBody @Valid UpdateCommentRequest request) {
        return ResponseService.getDataResponse(
                updateCommentUseCase.execute(
                        request, getCurrentUserSocialId(), commentId, recordId));
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글을 삭제합니다.")
    @DeleteMapping("/{recordId}/comment/{commentId}")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<DeleteCommentResponse> deleteComment(
            @PathVariable long recordId, @PathVariable long commentId) {
        return ResponseService.getDataResponse(
                deleteCommentUsecase.execute(getCurrentUserSocialId(), commentId, recordId));
    }
}
