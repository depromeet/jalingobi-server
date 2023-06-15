package depromeet.domain.user.adaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.repository.UserRepository;
import depromeet.domain.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAdaptorTest {
    @InjectMocks private UserAdaptor userAdaptor;

    @Mock private UserRepository userRepository;

    @Mock private UserService userService;

    @Test
    public void authUser_로그인_성공() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();

        Optional<User> optionalUser = Optional.of(user);

        given(userRepository.findByProfileEmail(any())).willReturn(optionalUser);
        given(userService.login(any(), any())).willReturn(user);

        // when
        User loginUser = userAdaptor.authUser(nickname, email, socialId, platform);

        // then
        assertThat(loginUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void authUser_회원가입_성공() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();

        Optional<User> optionalUser = Optional.empty();

        given(userRepository.findByProfileEmail(any())).willReturn(optionalUser);
        given(userService.register(any(), any(), any(), any())).willReturn(user);

        // when
        User registerUser = userAdaptor.authUser(nickname, email, socialId, platform);

        // then
        assertThat(registerUser).usingRecursiveComparison().isEqualTo(user);
    }
}
