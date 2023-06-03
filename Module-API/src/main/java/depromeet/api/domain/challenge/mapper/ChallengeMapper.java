package depromeet.api.domain.challenge.mapper;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.rule.domain.Rule;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeMapper {

    public Challenge toEntity(CreateChallengeRequest createChallengeRequest, User user) {
        return Challenge.createChallenge(
                createChallengeRequest.getCategory(),
                createChallengeRequest.getTitle(),
                createChallengeRequest.getPrice(),
                createChallengeRequest.getImageUrl(),
                createChallengeRequest.getHashtag(),
                createChallengeRequest.getAvailableCount(),
                user,
                Duration.builder()
                        .period(createChallengeRequest.getPeriod())
                        .startAt(createChallengeRequest.getStartAt())
                        .endAt(createChallengeRequest.getEndAt())
                        .build());
    }

    public CreateChallengeResponse toCreateChallengeResponse(
            Challenge challenge, List<Rule> rules) {
        return CreateChallengeResponse.of(challenge, rules);
    }
}
