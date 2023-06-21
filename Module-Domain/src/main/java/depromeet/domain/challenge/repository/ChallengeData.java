package depromeet.domain.challenge.repository;


import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChallengeData {

    private long id;
    private String title;
    private int currentPeopleCount;
    private int availablePeopleCount;
    private String imgUrl;
    private int price;
    private List<String> keywords;
    private LocalDate startAt;
    private int period;
    private String status;

    public ChallengeData(
            long id,
            String title,
            int currentPeopleCount,
            int availablePeopleCount,
            String imgUrl,
            int price,
            LocalDate startAt,
            int period) {
        this.id = id;
        this.title = title;
        this.currentPeopleCount = currentPeopleCount;
        this.availablePeopleCount = availablePeopleCount;
        this.imgUrl = imgUrl;
        this.price = price;
        this.startAt = startAt;
        this.period = period;
    }

    public void setKeywordNames(List<String> keywordNames) {
        this.keywords = keywordNames;
    }
}
