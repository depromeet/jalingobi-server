package depromeet.api.domain.record.controller;

import static depromeet.api.util.SecurityUtil.getCurrentUserSocialId;

import depromeet.api.domain.record.dto.request.CreateRecordRequest;
import depromeet.api.domain.record.dto.response.CreateRecordResponse;
import depromeet.api.domain.record.usecase.CreateRecordUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// @SecurityRequirement(name = "access")
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class RecordController {
    private final CreateRecordUseCase createRecordUseCase;

    @PostMapping("/{challengeRoomId}/create")
    public CreateRecordResponse createRecord(
            @PathVariable Long challengeRoomId,
            @RequestBody @Valid CreateRecordRequest createRecordRequest) {

        return createRecordUseCase.execute(
                challengeRoomId, getCurrentUserSocialId(), createRecordRequest);
    }
}
