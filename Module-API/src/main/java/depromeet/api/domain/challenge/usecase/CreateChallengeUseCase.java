package depromeet.api.domain.challenge.usecase;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.mapper.ChallengeMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.category.adaptor.CategoryAdaptor;
import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.adaptor.ChallengeAdaptor;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
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

    @Transactional
    public CreateChallengeResponse execute(CreateChallengeRequest request, String socialId) {
        User currentUser = userAdaptor.findUser(socialId);

        Category savedCategory = categoryAdaptor.findByName(request.getCategory().getName());
        Challenge challenge = challengeMapper.toEntity(request, socialId, savedCategory.getId());
        challenge.addRules(request.getChallengeRule());

        return challengeMapper.toCreateChallengeResponse(
                challengeAdaptor.save(challenge), savedCategory.getName());
    }
}
