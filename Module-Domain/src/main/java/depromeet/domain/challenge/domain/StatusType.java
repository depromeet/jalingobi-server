package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusType {
    NEW("new"),
    APPROACHING_DEADLINE("마감 임박"),
    AFTER_DAY("일 뒤 시작"),
    NOTHING("");

    private String name;
}
