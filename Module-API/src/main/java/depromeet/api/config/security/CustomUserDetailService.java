package depromeet.api.config.security;


import depromeet.domain.user.adaptor.UserAdaptor;
import depromeet.domain.user.domain.User;
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
        User account = userAdaptor.findUser(socialId);
        return new CustomUserDetails(account);
    }
}
