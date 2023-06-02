package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.entities.Role;

public interface RoleService {
    Role getByRoleName(String name);
}