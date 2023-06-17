package depromeet.api.domain.user.mapper;


import depromeet.api.domain.user.dto.response.GetUserInfoResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.user.domain.User;

@Mapper
public class UserMapper {

    public GetUserInfoResponse toGetUserInfoResponse(User user) {
        return GetUserInfoResponse.createGetUserInfoResponse(user);
    }
}
