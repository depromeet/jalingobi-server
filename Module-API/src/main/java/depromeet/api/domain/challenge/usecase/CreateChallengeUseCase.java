package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.category.adaptor.CategoryAdaptor;
import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.keyword.adaptor.KeywordAdaptor;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateChallengeUseCase {

    private final ChallengeMapper challengeMapper;
    private final ChallengeAdaptor challengeAdaptor;
    private final CategoryAdaptor categoryAdaptor;
    private final UserAdaptor userAdaptor;
    private final KeywordAdaptor keywordAdaptor;

    @Transactional
    public CreateChallengeResponse execute(CreateChallengeRequest request, String socialId) {
        return challengeMapper.toCreateChallengeResponse(
                challengeAdaptor.save(createChallenge(request, socialId)).getId());
    }

    public Challenge createChallenge(CreateChallengeRequest request, String socialId) {
        User currentUser = userAdaptor.findUser(socialId);
        List<Keyword> keywords = keywordAdaptor.findOrCreateKeywords(request.getKeywords());
        List<Category> categories =
                categoryAdaptor.findOrExceptionCategories(request.getCategory());

        Challenge challenge = challengeMapper.toEntity(request, socialId);

        challenge.addCategories(categories);
        challenge.addRules(request.getChallengeRule());
        challenge.addKeywords(keywords);

        return challenge;
    }
}
