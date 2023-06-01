package depromeet.domain.user.service;


import depromeet.common.annotation.DomainService;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.exception.DuplicatedEmailException;
import depromeet.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class UserService {

    private final UserRepository userRepository;

    public User register(String nickname, String email, String socialId, Platform platform) {
        User newUser = User.registerUser(nickname, email, socialId, platform);
        return userRepository.save(newUser);
    }

    public User login(Platform platform, Optional<User> findUser) {
        User user = findUser.get();

        if (!platform.equals(user.getSocial().getPlatform()))
            throw DuplicatedEmailException.EXCEPTION;

        return user;
    }
}
