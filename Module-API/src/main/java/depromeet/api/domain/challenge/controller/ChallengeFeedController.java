package depromeet.api.domain.challenge.controller;


import depromeet.api.domain.challenge.usecase.GetChallengeInfiniteScrollFeedUseCase;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import depromeet.domain.challenge.domain.ChallengeSlice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeFeedController {

    private final GetChallengeInfiniteScrollFeedUseCase getChallengeInfiniteScrollFeedUseCase;

    @GetMapping("/challenge/search")
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
