package depromeet.api.domain.feed.dto.response;


import depromeet.api.domain.feed.dto.ParticipatedChallenge;
import depromeet.domain.challenge.domain.ChallengeStatusType;
import depromeet.domain.config.BaseTime;
import depromeet.domain.userchallenge.domain.UserChallenge;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetMyChallengeListResponse {
    private List<ParticipatedChallenge> participatedChallengeList;

    public static GetMyChallengeListResponse of(List<UserChallenge> userChallengeList) {
        List<ParticipatedChallenge> participatedChallengeList =
                userChallengeList.stream()
                        // 날짜 오름차순 정렬
                        .sorted(Comparator.comparing(BaseTime::getCreatedAt))
                        .map(UserChallenge::getChallenge)
                        .map(ParticipatedChallenge::createParticipatedChallenge)
                        .collect(Collectors.toList());

        // 맨 앞에 내 방 정보 추가
        ParticipatedChallenge myRoom =
                new ParticipatedChallenge(
                        0L, "내 방", "baseImg", ChallengeStatusType.PROCEEDING.toString());
        participatedChallengeList.add(0, myRoom);

        return new GetMyChallengeListResponse(participatedChallengeList);
    }
}
