package depromeet.domain.user.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.exception.UserNotFoundException;
import depromeet.domain.user.repository.UserRepository;
import depromeet.domain.user.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adaptor
public class UserAdaptor {
    private final UserRepository userRepository;
    private final UserService userService;

    public User authUser(String nickname, String email, String socialId, Platform platform) {
        Optional<User> findUser = userRepository.findByProfileEmail(email);

        if (findUser.isPresent()) {
            return userService.login(platform, findUser.get());
        } else {
            return userService.register(nickname, email, socialId, platform);
        }
    }

    public User findUser(String socialId) {
        return userRepository
                .findBySocialId(socialId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void isExistUser(String socialId) {
        boolean isExist = userRepository.existsBySocialId(socialId);

        if (!isExist) {
            throw UserNotFoundException.EXCEPTION;
        }
    }
}
