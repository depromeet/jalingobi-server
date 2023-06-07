package depromeet.api.domain.challenge.mapper;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.ChallengeCategories;
import depromeet.domain.challenge.domain.Duration;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeMapper {

    public Challenge toEntity(CreateChallengeRequest createChallengeRequest, String createdBy) {
        return Challenge.createChallenge(
                createChallengeRequest.getTitle(),
                createChallengeRequest.getPrice(),
                createChallengeRequest.getImageUrl(),
                createChallengeRequest.getHashtag(),
                createChallengeRequest.getAvailableCount(),
                createdBy,
                new ArrayList<>(),
                new ChallengeCategories(),
                Duration.builder()
                        .period(createChallengeRequest.getPeriod())
                        .startAt(createChallengeRequest.getStartAt())
                        .endAt(createChallengeRequest.getEndAt())
                        .build());
    }

    public CreateChallengeResponse toCreateChallengeResponse(Long challengeId) {
        return CreateChallengeResponse.of(challengeId);
    }
}
