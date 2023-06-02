package kodlama.io.ecommerce.api.controllers;


import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.response.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public GetUserResponse register(@RequestBody CreateUserRequest request){
        return userService.register(request);
    }
}