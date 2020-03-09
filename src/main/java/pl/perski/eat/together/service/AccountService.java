package pl.perski.eat.together.service;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;

@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Account addAccount(Account account) {
        Account accountCreated = accountRepository.save(account);
        userRepository.save(User.builder().
                name("TO_CHANGE").
                userAccount(account).
                build());
        return accountCreated;
    }
}
