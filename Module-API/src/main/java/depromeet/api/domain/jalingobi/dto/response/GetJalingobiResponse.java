package depromeet.api.domain.jalingobi.dto.response;


import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetJalingobiResponse {
    @Schema(description = "전체 자린고비 정보 리스트")
    private List<Jalingobi> jalingobiList;

    @Schema(description = "현재 사용자 자린고비 레벨")
    private Level userLevel;

    public static GetJalingobiResponse of(List<Jalingobi> jalingobiList, Level userLevel) {
        return GetJalingobiResponse.builder()
                .jalingobiList(jalingobiList)
                .userLevel(userLevel)
                .build();
    }
}
