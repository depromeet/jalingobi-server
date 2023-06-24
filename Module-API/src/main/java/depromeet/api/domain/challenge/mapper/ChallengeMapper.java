package depromeet.api.domain.challenge.mapper;


import depromeet.api.domain.challenge.dto.request.CreateChallengeRequest;
import depromeet.api.domain.challenge.dto.request.UpdateChallengeRequest;
import depromeet.api.domain.challenge.dto.response.CreateChallengeResponse;
import depromeet.api.domain.challenge.dto.response.GetChallengeResponse;
import depromeet.api.domain.challenge.dto.response.UpdateChallengeResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.challenge.domain.Challenge;
import depromeet.domain.challenge.domain.ChallengeCategories;
import depromeet.domain.challenge.domain.Duration;
import depromeet.domain.challenge.domain.keyword.ChallengeKeywords;
import depromeet.domain.user.domain.User;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ChallengeMapper {

    public Challenge toEntity(CreateChallengeRequest createChallengeRequest, User user) {
        return Challenge.createChallenge(
                createChallengeRequest.getTitle(),
                createChallengeRequest.getPrice(),
                createChallengeRequest.getImageUrl(),
                new ChallengeKeywords(),
                createChallengeRequest.getAvailableCount(),
                user,
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

    public UpdateChallengeResponse toUpdateChallengeResponse(
            UpdateChallengeRequest updateChallengeRequest) {
        return UpdateChallengeResponse.of(updateChallengeRequest);
    }

    public GetChallengeResponse toGetChallengeResponse(Challenge challenge) {
        return GetChallengeResponse.of(challenge);
    }
}
