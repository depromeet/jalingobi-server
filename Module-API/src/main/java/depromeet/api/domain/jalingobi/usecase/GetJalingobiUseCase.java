package depromeet.api.domain.jalingobi.usecase;


import depromeet.api.domain.jalingobi.dto.response.GetJalingobiResponse;
import depromeet.api.domain.jalingobi.mapper.JalingobiMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.jalingobi.adaptor.JalingobiAdaptor;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetJalingobiUseCase {
    private final JalingobiMapper jalingobiMapper;
    private final UserAdaptor userAdaptor;
    private final JalingobiAdaptor jalingobiAdaptor;

    public GetJalingobiResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);
        Level userLevel = Level.getEnumTypeByScore(user.getScore());
        List<Jalingobi> jalingobiList = jalingobiAdaptor.findAll();

        return jalingobiMapper.toGetJalingobiResponse(jalingobiList, userLevel);
    }
}
