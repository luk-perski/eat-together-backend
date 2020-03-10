package pl.perski.eat.together.service.model;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
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
        if (userRepository.findById(user.getId()).isPresent()) {
            return userRepository.save(user);
        } else {
            return null; // todo hanle error when is no user in db
        }
    }

    @Override
    public User getById(int userId) {
        return null;
    }

    @Override
    public List<User> getManyByIds(List<Integer> idList) {
        return null;
    }
}
