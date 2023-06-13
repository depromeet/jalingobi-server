package depromeet.api.domain.record.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.emoji.dto.request.CreateEmojiRequest;
import depromeet.api.domain.emoji.dto.response.CreateEmojiResponse;
import depromeet.api.domain.emoji.usecase.CreateEmojiUseCase;
import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.dto.response.GetRecordResponse;
import depromeet.api.domain.record.usecase.CreateRecordUseCase;
import depromeet.api.domain.record.usecase.DeleteRecordUseCase;
import depromeet.api.domain.record.usecase.GetRecordUseCase;
import depromeet.api.domain.record.usecase.UpdateRecordUseCase;
import depromeet.common.exception.CustomExceptionStatus;
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

@Tag(name = "챌린지 기록", description = "챌린지 기록 API")
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class RecordController {
    private final CreateRecordUseCase createRecordUseCase;

    private final GetRecordUseCase getRecordUseCase;

    private final UpdateRecordUseCase updateRecordUseCase;
    private final DeleteRecordUseCase deleteRecordUseCase;

    private final CreateEmojiUseCase createEmojiUseCase;

    @Operation(summary = "챌린지 지출을 기록하는 API")
    @PostMapping("/{challengeRoomId}/create")
    public Response<CreateRecordResponse> createRecord(
            @PathVariable Long challengeRoomId,
            @RequestBody @Valid CreateRecordRequest createRecordRequest) {

        return ResponseService.getDataResponse(
                createRecordUseCase.execute(
                        challengeRoomId, getCurrentUserSocialId(), createRecordRequest));
    }

    @Operation(summary = "챌린지 지출 상세보기 API", description = "기록에 대한 본문 및 댓글들을 보여줍니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    @GetMapping("/{challengeRoomId}/{recordId}")
    public Response<GetRecordResponse> getRecord(
            @PathVariable("challengeRoomId") Long challengeRoomId,
            @PathVariable("recordId") Long recordId) {

        return ResponseService.getDataResponse(getRecordUseCase.execute());
    }

    @Operation(summary = "챌린지 지출을 수정하는 API")
    @PatchMapping("/{recordId}")
    public Response<CustomExceptionStatus> updateRecord(
            @PathVariable Long recordId,
            @RequestBody @Valid CreateRecordRequest updateRecordRequest) {

        updateRecordUseCase.execute(recordId, getCurrentUserSocialId(), updateRecordRequest);
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }

    @Operation(summary = "챌린지 지출을 삭제하는 API")
    @DeleteMapping("/{recordId}")
    public Response<CustomExceptionStatus> deleteRecord(@PathVariable Long recordId) {

        deleteRecordUseCase.execute(recordId, getCurrentUserSocialId());
        return ResponseService.getDataResponse(CustomExceptionStatus.SUCCESS);
    }

    @PutMapping("/{recordId}/emojis")
    public Response<CreateEmojiResponse> createRecordEmoji(
            @PathVariable Long recordId,
            @RequestBody @Valid CreateEmojiRequest createEmojiRequest) {
        return ResponseService.getDataResponse(
                createEmojiUseCase.execute(getCurrentUserSocialId(), recordId, createEmojiRequest));
    }
}
