package pl.perski.eat.together.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Account addAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Account accountCreated = accountRepository.save(account);
        userRepository.save(User.builder().
                name(account.getUsername()).
                email(account.getEmail()).
                userAccount(account).
                build());
        return accountCreated;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int accountId) {
        return getAccountById(accountId);
    }

    @Override
    public Account addEventToAccount(int accountId, int eventId) {
        Account account = getAccountById(accountId);
        account.addEventToHistory(eventId);
        return accountRepository.save(account);
    }

    private Account getAccountById(int id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new EntityNotFoundException(Account.class, "id", Integer.toString(id));
        }
        return account;
    }
}
