package depromeet.api.domain.mypage.usecase;


import depromeet.api.domain.mypage.dto.response.GetJalingobiImgResponse;
import depromeet.api.domain.mypage.mapper.MyPageMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.jalingobi.adaptor.JalingobiAdaptor;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetJalingobiImgUseCase {
    private final MyPageMapper myPageMapper;
    private final UserAdaptor userAdaptor;
    private final JalingobiAdaptor jalingobiAdaptor;

    public GetJalingobiImgResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);
        Level userLevel = Level.getEnumTypeByScore(user.getScore());
        Jalingobi jalingobi = jalingobiAdaptor.findJalingobi(userLevel);

        return myPageMapper.toGetJalingobiImgResponse(jalingobi.getImgUrl());
    }
}
