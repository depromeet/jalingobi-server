package depromeet.api.domain.user.usecase;


import depromeet.api.domain.user.dto.response.GetUserInfoResponse;
import depromeet.api.domain.user.mapper.UserMapper;
import depromeet.common.annotation.UseCase;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetUserInfoUseCase {

    private final UserAdaptor userAdaptor;
    private final UserMapper userMapper;

    public GetUserInfoResponse execute(String socialId) {
        User user = userAdaptor.findUser(socialId);

        return userMapper.toGetUserInfoResponse(user);
    }
}
