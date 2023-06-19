package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.api.domain.challenge.mapper.ChallengeRuleMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.category.adaptor.CategoryAdaptor;
import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.CategoryType;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.keyword.adaptor.KeywordAdaptor;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.adaptor.UserChallengeAdaptor;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateChallengeUseCase {

    private final ChallengeMapper challengeMapper;
    private final ChallengeRuleMapper challengeRuleMapper;
    private final ChallengeAdaptor challengeAdaptor;
    private final CategoryAdaptor categoryAdaptor;
    private final UserAdaptor userAdaptor;
    private final KeywordAdaptor keywordAdaptor;
    private final UserChallengeAdaptor userChallengeAdaptor;

    @Transactional
    public CreateChallengeResponse execute(CreateChallengeRequest request, String socialId) {
        User currentUser = userAdaptor.findUser(socialId);
        Challenge savedChallenge = challengeAdaptor.save(createChallenge(request, socialId));

        UserChallenge userChallenge =
                UserChallenge.createUserChallenge(
                        currentUser,
                        savedChallenge,
                        currentUser.getProfileImgUrl(),
                        currentUser.getProfileNickname());
        userChallengeAdaptor.joinChallenge(userChallenge);

        return challengeMapper.toCreateChallengeResponse(savedChallenge.getId());
    }

    public Challenge createChallenge(CreateChallengeRequest request, String socialId) {

        List<Keyword> keywords = keywordAdaptor.findOrCreateKeywords(request.getKeywords());
        List<Category> categories =
                categoryAdaptor.findOrExceptionCategories(
                        request.getCategory().stream()
                                .map(name -> CategoryType.of(name).toString())
                                .collect(Collectors.toList()));

        Challenge challenge = challengeMapper.toEntity(request, socialId);

        challenge.addCategories(categories);
        challenge.addRules(challengeRuleMapper.toEntities(request.getChallengeRule()));
        challenge.addKeywords(keywords);

        return challenge;
    }
}
