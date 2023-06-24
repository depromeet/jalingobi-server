package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ChallengeStatusType {
    RECRUITING("모집중"),
    PROCEEDING("진행중"),
    CLOSE("종료");

    String value;
}
