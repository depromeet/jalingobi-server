package depromeet.api.config.security;


import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserAdaptor userAdaptor;

    @Override
    public UserDetails loadUserByUsername(String socialId) throws UsernameNotFoundException {
        Optional<User> account = userAdaptor.findUser(socialId);
        if (!account.isPresent())
            throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        return new CustomUserDetails(account.get());
    }
}
