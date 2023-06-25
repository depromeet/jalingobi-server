package depromeet.api.domain.challenge.controller;


import depromeet.api.domain.challenge.usecase.GetChallengeInfiniteScrollFeedUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import depromeet.domain.challenge.domain.ChallengeSlice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "챌린지 탐색", description = "챌린지 탐색 API")
public class ChallengeFeedController {

    private final GetChallengeInfiniteScrollFeedUseCase getChallengeInfiniteScrollFeedUseCase;

    @GetMapping("/challenge/search")
    @Operation(summary = "챌린지 탐색 API", description = "조건에 따라 챌린지를 탐색합니다.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200"),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못된 요청값을 전달한 경우",
                        content = @Content())
            })
    public Response<ChallengeSlice> searchChallenges(
            @RequestParam(required = false) final String category,
            @RequestParam(required = false, defaultValue = "recruit") String filter,
            @RequestParam(required = false) String sortType,
            @PageableDefault final Pageable pageable) {
        return ResponseService.getDataResponse(
                getChallengeInfiniteScrollFeedUseCase.execute(
                        category, filter, sortType, pageable));
    }
}
