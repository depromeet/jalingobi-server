package depromeet.api.domain.record.controller;

import static depromeet.api.util.AuthenticationUtil.getCurrentUserSocialId;

import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.request.UpdateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.dto.response.GetRecordResponse;
import depromeet.api.domain.record.usecase.CreateRecordUseCase;
import depromeet.api.domain.record.usecase.DeleteRecordUseCase;
import depromeet.api.domain.record.usecase.GetRecordUseCase;
import depromeet.api.domain.record.usecase.UpdateRecordUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "챌린지 기록", description = "챌린지 기록 API")
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {
    private final CreateRecordUseCase createRecordUseCase;

    private final GetRecordUseCase getRecordUseCase;

    private final UpdateRecordUseCase updateRecordUseCase;
    private final DeleteRecordUseCase deleteRecordUseCase;

    @PostMapping("/{challengeId}")
    @Operation(summary = "챌린지 지출을 기록하는 API")
    public Response<CreateRecordResponse> createRecord(
            @PathVariable Long challengeId,
            @RequestBody @Valid CreateRecordRequest createRecordRequest) {

        return ResponseService.getDataResponse(
                createRecordUseCase.execute(
                        challengeId, getCurrentUserSocialId(), createRecordRequest));
    }

    @GetMapping("/{challengeId}/{recordId}")
    @Operation(summary = "챌린지 지출 상세보기 API", description = "기록에 대한 본문 및 댓글들을 보여줍니다.")
    public Response<GetRecordResponse> getRecord(
            @PathVariable("challengeId") Long challengeId,
            @PathVariable("recordId") Long recordId) {

        return ResponseService.getDataResponse(
                getRecordUseCase.execute(getCurrentUserSocialId(), challengeId, recordId));
    }

    @PatchMapping("/{recordId}")
    @Operation(summary = "챌린지 지출을 수정하는 API")
    public Response updateRecord(
            @PathVariable Long recordId,
            @RequestBody @Valid UpdateRecordRequest updateRecordRequest) {

        updateRecordUseCase.execute(recordId, getCurrentUserSocialId(), updateRecordRequest);
        return ResponseService.getSuccessResponse();
    }

    @DeleteMapping("/{recordId}")
    @Operation(summary = "챌린지 지출을 삭제하는 API")
    public Response deleteRecord(@PathVariable Long recordId) {

        deleteRecordUseCase.execute(recordId, getCurrentUserSocialId());
        return ResponseService.getSuccessResponse();
    }
}
