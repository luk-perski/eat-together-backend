package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

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
    public List<UserData> getAll() {
        return userRepository.findAll();
    }

//    @Override
//    public UserData add(UserData userData) {
//        userData.setUserAccountData(accountRepository.save(new AccountData()));
//        return userRepository.save(userData);
//    }

    @Override
    public UserData update(UserData userData) {
        getUserById(userData.getId());
        return userRepository.save(userData);
    }

    @Override
    public UserData getById(int userId) {
        return getUserById(userId);
    }

    @Override
    public List<UserData> getManyByIds(List<Integer> idList) {
        return null;
    }

    private UserData getUserById(int id) {
        UserData userData = userRepository.findById(id).orElse(null);
        if (userData == null) {
            throw new EntityNotFoundException(UserData.class, "id", Integer.toString(id));
        }
        return userData;
    }
}
