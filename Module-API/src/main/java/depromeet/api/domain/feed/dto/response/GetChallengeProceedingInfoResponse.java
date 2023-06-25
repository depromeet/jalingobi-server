package depromeet.api.domain.feed.dto.response;


import depromeet.domain.userchallenge.domain.UserChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GetChallengeProceedingInfoResponse {
    @Schema(description = "목표 지출액", example = "100000")
    private Integer goalCharge;

    @Schema(description = "현재 지출액", example = "65000")
    private Integer currentCharge;

    @Schema(description = "퍼센트(%)", example = "65")
    private Integer percent;

    @Schema(description = "남은 기간", example = "11")
    private Long dueDay;

    public static GetChallengeProceedingInfoResponse of(UserChallenge userChallenge) {
        Integer goalCharge = userChallenge.getChallenge().getPrice();
        Integer currentCharge = userChallenge.getCurrentCharge();
        Integer percent = (currentCharge * 100) / goalCharge;

        LocalDate today = LocalDate.now();
        LocalDate endDate = userChallenge.getChallenge().getDuration().getEndAt();
        Long dueDay = ChronoUnit.DAYS.between(today, endDate);

        return new GetChallengeProceedingInfoResponse(goalCharge, currentCharge, percent, dueDay);
    }
}
