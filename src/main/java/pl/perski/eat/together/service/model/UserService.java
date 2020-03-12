package pl.perski.eat.together.service.model;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;
import pl.perski.eat.together.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        user.setUserAccount(accountRepository.save(new Account()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public User getById(int userId) {
        return getUserById(userId);
    }

    @Override
    public List<User> getManyByIds(List<Integer> idList) {
        return null;
    }

    private User getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", Integer.toString(id));
        }
        return user;
    }
}
