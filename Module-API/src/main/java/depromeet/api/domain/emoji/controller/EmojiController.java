package depromeet.api.domain.emoji.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.emoji.dto.request.CreateEmojiRequest;
import depromeet.api.domain.emoji.dto.request.DeleteEmojiRequest;
import depromeet.api.domain.emoji.usecase.CreateEmojiUseCase;
import depromeet.api.domain.emoji.usecase.DeleteEmojiUseCase;
import depromeet.common.response.CommonResponse;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이모지", description = "이모지 CUD API")
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class EmojiController {

    private final CreateEmojiUseCase createEmojiUseCase;
    private final DeleteEmojiUseCase deleteEmojiUseCase;

    @PutMapping("/{recordId}/emoji")
    @Operation(summary = "이모지 생성 및 수정 API", description = "이모지를 생성 및 수정합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public CommonResponse createRecordEmoji(
            @PathVariable long recordId,
            @RequestBody @Valid CreateEmojiRequest createEmojiRequest) {
        createEmojiUseCase.execute(getCurrentUserSocialId(), recordId, createEmojiRequest);
        return ResponseService.getSuccessResponse();
    }

    @DeleteMapping("/{recordId}/emoji")
    @Operation(summary = "이모지 삭제 API", description = "이미 등록된 이모지를 삭제합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public CommonResponse deleteRecordEmoji(
            @PathVariable long recordId, @RequestBody @Valid DeleteEmojiRequest request) {
        deleteEmojiUseCase.execute(getCurrentUserSocialId(), recordId, request.getType());
        return ResponseService.getSuccessResponse();
    }
}
