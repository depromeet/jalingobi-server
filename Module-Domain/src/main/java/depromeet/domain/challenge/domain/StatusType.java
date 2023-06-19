package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusType {
    NEW("new"),
    APPROACHING_DEADLINE("마감임박"),
    COMING_SOON("오픈 예정"),
    NOTHING("해당없음");

    private String name;
}
