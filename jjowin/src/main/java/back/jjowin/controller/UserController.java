package back.jjowin.controller;

import back.jjowin.domain.User;
import back.jjowin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public Long signup (@RequestBody User user){
        return userService.signUp(user);
    }
}
