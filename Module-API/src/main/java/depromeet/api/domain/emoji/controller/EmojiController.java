package depromeet.api.domain.emoji.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.emoji.dto.request.CreateEmojiRequest;
import depromeet.api.domain.emoji.dto.request.DeleteEmojiRequest;
import depromeet.api.domain.emoji.dto.response.CreateEmojiResponse;
import depromeet.api.domain.emoji.dto.response.DeleteEmojiResponse;
import depromeet.api.domain.emoji.usecase.CreateEmojiUseCase;
import depromeet.api.domain.emoji.usecase.DeleteEmojiUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이모지", description = "이모지 API")
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class EmojiController {

    private final CreateEmojiUseCase createEmojiUseCase;
    private final DeleteEmojiUseCase deleteEmojiUseCase;

    @PutMapping("/{recordId}/emoji")
    public Response<CreateEmojiResponse> createRecordEmoji(
            @PathVariable long recordId,
            @RequestBody @Valid CreateEmojiRequest createEmojiRequest) {
        return ResponseService.getDataResponse(
                createEmojiUseCase.execute(getCurrentUserSocialId(), recordId, createEmojiRequest));
    }

    @DeleteMapping("/{recordId}/emoji")
    public Response<DeleteEmojiResponse> deleteRecordEmoji(
            @PathVariable long recordId, @RequestBody @Valid DeleteEmojiRequest request) {
        return ResponseService.getDataResponse(
                deleteEmojiUseCase.execute(getCurrentUserSocialId(), recordId, request.getType()));
    }
}
