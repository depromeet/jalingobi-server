package depromeet.api.domain.jalingobi.dto.response;


import depromeet.api.domain.jalingobi.dto.JalingobiInfo;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetJalingobiResponse {
    @Schema(description = "전체 자린고비 정보 리스트")
    private List<JalingobiInfo> jalingobiList;

    @Schema(description = "현재 사용자 자린고비 레벨", example = "1")
    private Integer userLevel;

    public static GetJalingobiResponse of(List<Jalingobi> jalingobiList, Level userLevel) {

        List<JalingobiInfo> newJalingobiList =
                jalingobiList.stream()
                        .map(JalingobiInfo::createJalingobiInfo)
                        .collect(Collectors.toList());

        return GetJalingobiResponse.builder()
                .jalingobiList(newJalingobiList)
                .userLevel(userLevel.getValue())
                .build();
    }
}
