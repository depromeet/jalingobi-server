package depromeet.api.domain.jalingobi.mapper;


import depromeet.api.domain.jalingobi.dto.response.GetJalingobiResponse;
import depromeet.api.domain.jalingobi.dto.response.GetSmallTalkResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class JalingobiMapper {
    public GetJalingobiResponse toGetJalingobiResponse(
            List<Jalingobi> jalingobiList, Level userLevel) {
        return GetJalingobiResponse.of(jalingobiList, userLevel);
    }

    public GetSmallTalkResponse toGetSmallTalkResponse(Level level, String smallTalk) {
        return GetSmallTalkResponse.of(level, smallTalk);
    }
}
