package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.entities.User;
import kodlama.io.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsManager implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user=getByUsername(username);
        List<SimpleGrantedAuthority> authorities=user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

    private User getByUsername(String username){
        return repository.findByUsername(username).orElseThrow();
    }
}
