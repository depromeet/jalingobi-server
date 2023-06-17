package depromeet.domain.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum NicknameType {
    ONE("내 통장잔고 반쪼가리"),
    TWO("마술같이 한순간에 사라지는 돈"),
    THREE("지금 거의 거렁뱅이에요"),
    FOUR("여유가 없어 뵈는 통장"),
    FIVE("돈이 없어서 행복을 못 사"),
    SIX("아무도 나를 모르고 돈이 많은"),
    SEVEN("작고 귀여운 내 통장"),
    EIGHT("인간의 욕심은 끝이 없고"),
    NINE("가난을 거꾸로 읽으면"),
    TEN("기부 me 머니"),
    ELEVEN("감정이 잇는 ATM"),
    TWELVE("일하지 않고 돈이 갖고 싶어"),
    THIRTEEN("장래희망 돈 많은 백수"),
    FOURTEEN("그럼 20000원"),
    FIFTEEN("행복한 71억"),
    SIXTEEN("구멍 뚫린 지갑");

    private String name;
}
