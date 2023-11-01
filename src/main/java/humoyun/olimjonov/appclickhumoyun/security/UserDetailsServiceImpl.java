package humoyun.olimjonov.appclickhumoyun.security;

import humoyun.olimjonov.appclickhumoyun.entity.Users;
import humoyun.olimjonov.appclickhumoyun.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
