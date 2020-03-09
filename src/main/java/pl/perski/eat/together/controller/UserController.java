package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.service.IUserService;
import pl.perski.eat.together.service.model.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final IUserService userService;

    public UserController(UserRepository userRepository, UserService userService, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/users")
    public User update(@Valid @RequestBody User user){
        return userService.update(user);
    }
}
