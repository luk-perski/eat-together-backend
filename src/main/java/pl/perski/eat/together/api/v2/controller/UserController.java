package pl.perski.eat.together.api.v2.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.api.v2.dto.user.UserDtoGet;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;
import pl.perski.eat.together.api.v2.dto.user.UserMapper;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.service.IUserService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "users", description = "the Users API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDtoGet> getUsers() {
        return (userMapper.toUsersDTOs(userService.getAll()));
    }

    @GetMapping("/users/{userId}")
    public UserDtoGet getUserById(@PathVariable int userId) {
        return userMapper.toUserDtoGet(userService.getById(userId));
    }

    @GetMapping("/users/account")
    public UserDtoGet getSignedUserByEmail(@Parameter(hidden = true) Authentication authentication) {
        return userMapper.toUserDtoGet(userService.getByAccountEmail(authentication.getName()));
    }

    @PatchMapping("/users")
    public UserDtoGet update(@Valid @RequestBody UserDtoPost request, @Parameter(hidden = true) Authentication authentication) {
        return userMapper.toUserDtoGet(userService.update(request, authentication.getName(), userMapper));
    }
}
