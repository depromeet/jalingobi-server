package depromeet.api.domain.jalingobi.dto;


import depromeet.domain.jalingobi.domain.Jalingobi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class JalingobiInfo {
    private Long jalingobiId;

    private Integer level;

    private String name;

    private String acquisitionCondition;

    public static JalingobiInfo createJalingobiInfo(Jalingobi jalingobi) {

        return JalingobiInfo.builder()
                .jalingobiId(jalingobi.getId())
                .level(jalingobi.getLevel().getValue())
                .name(jalingobi.getName())
                .acquisitionCondition(jalingobi.getAcquisitionCondition())
                .build();
    }
}
