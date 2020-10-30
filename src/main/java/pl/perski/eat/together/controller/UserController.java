package pl.perski.eat.together.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.service.IUserService;
import pl.perski.eat.together.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "groups", description = "the Groups API")
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserData> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{userId}")
    public UserData getUserById(@PathVariable int userId) {
        return userService.getById(userId);
    }

    @GetMapping("/users/account")
    public UserData getUserByAccountEmail(@Parameter(hidden = true) Authentication authentication) {
        return userService.getByAccountEmail(authentication.getName());
    }

    @PatchMapping("/users")
    public UserData update(@Valid @RequestBody UserData userData, @Parameter(hidden = true) Authentication authentication) {
        return userService.update(userData, authentication.getName());
    }
}
