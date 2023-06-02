package kodlama.io.ecommerce.utils;

import kodlama.io.ecommerce.entities.User;
import kodlama.io.ecommerce.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SystemHelper {
    private final UserRepository userRepository;


    public SystemHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.nonNull(authentication)){
            String username = authentication.getName();

            User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));

            return user;
        }

        throw new RuntimeException("USER NOT FOUND");
    }
}
