package depromeet.api.domain.challenge.controller;


import depromeet.api.domain.challenge.usecase.GetChallengeInfiniteScrollFeedUseCase;
import depromeet.common.annotation.ValidEnum;
import depromeet.common.response.Response;
import depromeet.common.response.ResponseService;
import depromeet.domain.challenge.domain.CategoryType;
import depromeet.domain.challenge.domain.ChallengeSlice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public Response<ChallengeSlice> searchChallenges(
            @RequestParam(required = false, defaultValue = "")
                    @ValidEnum(enumClass = CategoryType.class, message = "유효하지 않은 카테고리 파라미터입니다.")
                    final CategoryType category,
            @RequestParam(required = false, defaultValue = "recruit") String filter,
            @RequestParam(required = false, defaultValue = "") String sortType,
            @PageableDefault @Parameter(hidden = true) final Pageable pageable) {
        return ResponseService.getDataResponse(
                getChallengeInfiniteScrollFeedUseCase.execute(
                        category, filter, sortType, pageable));
    }
}
