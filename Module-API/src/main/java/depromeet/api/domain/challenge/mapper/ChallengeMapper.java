package depromeet.api.domain.challenge.mapper;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.Duration;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeMapper {

    public Challenge toEntity(
            CreateChallengeRequest createChallengeRequest, String createdBy, Long categoryId) {
        return Challenge.createChallenge(
                categoryId,
                createChallengeRequest.getTitle(),
                createChallengeRequest.getPrice(),
                createChallengeRequest.getImageUrl(),
                createChallengeRequest.getHashtag(),
                createChallengeRequest.getAvailableCount(),
                createdBy,
                new ArrayList<>(),
                Duration.builder()
                        .period(createChallengeRequest.getPeriod())
                        .startAt(createChallengeRequest.getStartAt())
                        .endAt(createChallengeRequest.getEndAt())
                        .build());
    }

    public CreateChallengeResponse toCreateChallengeResponse(Challenge challenge, String category) {
        return CreateChallengeResponse.of(challenge, category);
    }
}
