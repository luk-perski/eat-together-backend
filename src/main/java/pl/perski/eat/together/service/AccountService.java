package pl.perski.eat.together.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account addAccount(Account account){
        Account accountCreated =  accountRepository.save(account);
        userRepository.save(User.builder().
                name("TO_CHANGE").
                userAccount(account).
                build());
        return accountCreated;
    }
}
