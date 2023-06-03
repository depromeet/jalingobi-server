package depromeet.domain.user.service;


import depromeet.common.annotation.DomainService;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.exception.AccountAlreadyExist;
import depromeet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class UserService {

    private final UserRepository userRepository;

    public User login(Platform platform, User user) {
        if (!platform.equals(user.getSocial().getPlatform())) throw AccountAlreadyExist.EXCEPTION;

        return user;
    }

    public User register(String nickname, String email, String socialId, Platform platform) {
        User newUser = User.registerUser(nickname, email, socialId, platform);
        return userRepository.save(newUser);
    }
}
