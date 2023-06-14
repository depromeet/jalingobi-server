package depromeet.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import depromeet.domain.user.domain.Platform;
import depromeet.domain.user.domain.Profile;
import depromeet.domain.user.domain.Social;
import depromeet.domain.user.domain.User;
import depromeet.domain.user.exception.AccountAlreadyExist;
import depromeet.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks private UserService userService;

    @Mock private UserRepository userRepository;

    @Test
    public void register_성공() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();

        given(userRepository.save(any())).willReturn(user);

        // when
        User registerUser = userService.register(nickname, email, socialId, platform);

        // then
        assertThat(registerUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void login_성공() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();

        // when then
        User loginUser = userService.login(Platform.KAKAO, user);

        // then
        assertThat(loginUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void login_실패_타플랫폼_가입된계정() throws Exception {
        // given
        Platform platform = Platform.KAKAO;
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";

        Profile profile = Profile.createProfile(nickname, email);
        Social social = Social.createSocial(socialId, platform);
        User user = User.builder().profile(profile).social(social).build();

        // when then
        Assertions.assertThrows(
                AccountAlreadyExist.class,
                () -> {
                    userService.login(Platform.APPLE, user);
                });
    }
}
