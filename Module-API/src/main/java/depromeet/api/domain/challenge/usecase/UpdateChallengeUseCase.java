package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.UpdateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.category.adaptor.CategoryAdaptor;
import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.CategoryType;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.exception.ChallengeNotBelongToUserException;
import depromeet.domain.keyword.adaptor.KeywordAdaptor;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.rule.domain.ChallengeRule;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateChallengeUseCase {

    private final ChallengeAdaptor challengeAdaptor;
    private final KeywordAdaptor keywordAdaptor;
    private final UserAdaptor userAdaptor;
    private final CategoryAdaptor categoryAdaptor;
    private final ChallengeMapper challengeMapper;

    @Transactional
    public UpdateChallengeResponse execute(UpdateChallengeRequest request, String socialId) {
        User user = userAdaptor.findUser(socialId);
        Challenge challenge = challengeAdaptor.findChallenge(request.getChallengeId());
        if (challenge.isNotWrittenBy(socialId)) {
            throw ChallengeNotBelongToUserException.EXCEPTION;
        }
        challenge.validateUpdate(challenge.getDuration().getStartAt());

        List<Keyword> keywords = keywordAdaptor.findOrCreateKeywords(request.getKeywords());
        List<Category> categories =
                categoryAdaptor.findOrExceptionCategories(
                        request.getCategories().stream()
                                .map(name -> CategoryType.of(name).toString())
                                .collect(Collectors.toList()));
        List<ChallengeRule> challengeRules = ChallengeRule.createRule(request.getRules());

        challenge.updateTitle(request.getTitle());
        challenge.updatePrice(request.getPrice());
        challenge.updateImgUrl(request.getImgUrl());
        challenge.updateAvailableCount(request.getAvailableCount());
        challenge.updateChallengeRules(challengeRules);
        challenge.updateChallengeCategories(categories);
        challenge.updateKeywords(keywords);
        challenge.updateDuration(request.getPeriod(), request.getStartAt(), request.getEndAt());

        return challengeMapper.toUpdateChallengeResponse(request);
    }
}
