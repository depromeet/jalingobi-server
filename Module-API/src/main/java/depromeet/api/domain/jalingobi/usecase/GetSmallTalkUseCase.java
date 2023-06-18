package depromeet.api.domain.jalingobi.usecase;


import depromeet.api.domain.jalingobi.dto.response.GetSmallTalkResponse;
import depromeet.api.domain.jalingobi.mapper.JalingobiMapper;
import depromeet.api.util.RandomUtil;
import depromeet.common.annotation.UseCase;
import depromeet.domain.jalingobi.adaptor.SmallTalkAdaptor;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.jalingobi.domain.SmallTalk;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetSmallTalkUseCase {
    private final JalingobiMapper jalingobiMapper;
    private final UserAdaptor userAdaptor;
    private final SmallTalkAdaptor smallTalkAdaptor;

    public GetSmallTalkResponse execute(String socialId, Long jalingobiId) {
        User user = userAdaptor.findUser(socialId);
        Level userLevel = Level.getEnumTypeByScore(user.getScore());

        List<SmallTalk> smallTalks = smallTalkAdaptor.findSmallTalkByJalingobiId(jalingobiId);
        SmallTalk randomSmallTalk = RandomUtil.getRandomItem(smallTalks);

        return jalingobiMapper.toGetSmallTalkResponse(randomSmallTalk.getContent());
    }
}
