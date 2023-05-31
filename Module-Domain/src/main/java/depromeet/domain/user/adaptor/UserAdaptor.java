package depromeet.domain.user.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.exception.DuplicatedEmailException;
import depromeet.domain.user.exception.UserNotFoundException;
import depromeet.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adaptor
public class UserAdaptor {
    private final UserRepository userRepository;

    public User authUser(String nickname, String email, String socialId, Platform platform) {
        Optional<User> findUser = userRepository.findByProfileEmail(email);

        if (findUser.isPresent()) {
            User user = findUser.get();

            if (!platform.equals(user.getSocial().getPlatform()))
                throw DuplicatedEmailException.EXCEPTION;

            return user;
        } else {
            User newUser = User.registerUser(nickname, email, socialId, platform);
            return userRepository.save(newUser);
        }
    }

    public User findUser(String socialId) {
        return userRepository
                .findBySocialId(socialId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
