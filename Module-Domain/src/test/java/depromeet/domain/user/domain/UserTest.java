package depromeet.domain.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
    public void registerUser_성공() throws Exception {
        // given
        String nickname = "tester";
        String email = "test@test";
        String socialId = "1234";
        Platform platform = Platform.KAKAO;

        // when
        User registerUser = User.registerUser(nickname, email, socialId, platform);

        // then
        assertThat(registerUser.getProfile().getNickname()).isEqualTo(nickname);
        assertThat(registerUser.getProfile().getEmail()).isEqualTo(email);
        assertThat(registerUser.getSocial().getId()).isEqualTo(socialId);
        assertThat(registerUser.getSocial().getPlatform()).isEqualTo(platform);
        assertThat(registerUser.getScore()).isEqualTo(1); // 가입 시 score는 1
    }
}
