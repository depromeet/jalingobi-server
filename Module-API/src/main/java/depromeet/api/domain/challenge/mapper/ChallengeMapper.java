package depromeet.api.domain.challenge.mapper;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.rule.domain.Rule;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeMapper {

    public Challenge toEntity(CreateChallengeRequest createChallengeRequest) {
        return Challenge.builder()
                .category(createChallengeRequest.getCategory())
                .title(createChallengeRequest.getTitle())
                .imgUrl(createChallengeRequest.getImageUrl())
                .hashtag(createChallengeRequest.getHashtag())
                .availableCount(createChallengeRequest.getAvailableCount())
                .duration(
                        Duration.builder()
                                .period(createChallengeRequest.getPeriod())
                                .startAt(createChallengeRequest.getStartAt())
                                .endAt(createChallengeRequest.getEndAt())
                                .build())
                .build();
    }

    public CreateChallengeResponse toCreateChallengeResponse(
            Challenge challenge, List<Rule> rules) {
        return CreateChallengeResponse.builder()
                .id(challenge.getId())
                .category(challenge.getCategory())
                .title(challenge.getTitle())
                .imgUrl(challenge.getImgUrl())
                .hashtag(challenge.getHashtag())
                .availableCount(challenge.getAvailableCount())
                .challengeRules(rules)
                .period(challenge.getDuration().getPeriod())
                .startAt(challenge.getDuration().getStartAt())
                .endAt(challenge.getDuration().getEndAt())
                .build();
    }
}
