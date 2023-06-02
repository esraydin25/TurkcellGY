package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.response.GetUserResponse;
import kodlama.io.ecommerce.entities.User;

public interface UserService {
    GetUserResponse register(CreateUserRequest request);
    User getByUsername(String username);
}
