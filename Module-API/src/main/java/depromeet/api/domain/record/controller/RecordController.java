package depromeet.api.domain.record.controller;

import static depromeet.api.util.SecurityUtil.getCurrentUserSocialId;

import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.usecase.CreateRecordUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// @SecurityRequirement(name = "access")
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class RecordController {
    private final CreateRecordUseCase createRecordUseCase;
    private final ResponseService responseService;

    @PostMapping("/{challengeRoomId}/create")
    public Response<CreateRecordResponse> createRecord(
            @PathVariable Long challengeRoomId,
            @RequestBody @Valid CreateRecordRequest createRecordRequest) {

        return responseService.getDataResponse(
                createRecordUseCase.execute(
                        challengeRoomId, getCurrentUserSocialId(), createRecordRequest));
    }
}
