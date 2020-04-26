package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.service.IUserService;
import pl.perski.eat.together.service.UserService;

import javax.validation.Valid;
import java.util.List;

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

//    @PostMapping("/users")
//    public UserData add(@Valid @RequestBody UserData userData) {
//        return userService.add(userData);
//    }

    @PutMapping("/users")
    public UserData update(@Valid @RequestBody UserData userData) {
        return userService.update(userData);
    }
}
