package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.RoleService;
import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.response.GetUserResponse;
import kodlama.io.ecommerce.entities.User;
import kodlama.io.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public GetUserResponse register(CreateUserRequest request) {
     User user=new User();
     user.setUsername(request.getUsername());
     user.setPassword(passwordEncoder.encode(request.getPassword()));
     user.getRoles().add(roleService.getByRoleName("ADMIN"));
     repository.save(user);

     return new GetUserResponse(user.getUsername(), user.getPassword());
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
    }
}
