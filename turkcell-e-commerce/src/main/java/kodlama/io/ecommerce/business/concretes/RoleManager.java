package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.RoleService;
import kodlama.io.ecommerce.entities.Role;
import kodlama.io.ecommerce.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private final RoleRepository repository;
    @Override
    public Role getByRoleName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new RuntimeException("ROLE NOT FOUND"));
    }
}
