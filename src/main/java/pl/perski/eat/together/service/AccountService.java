package pl.perski.eat.together.service;

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

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int accountId) {
        Account account = getAccountById(accountId);
        return account;
    }

    @Override
    public Account addEventToAccount(int accountId, int eventId) {
        Account account = getAccountById(accountId); //todo obsługa
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
