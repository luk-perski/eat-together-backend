package pl.perski.eat.together.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.List;

@Service()
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Boolean addAccount(AccountData accountData, UserData userData) {
        accountData.setUserData(userRepository.save(userData));
        accountData.setPassword(bCryptPasswordEncoder.encode(accountData.getPassword()));
        accountRepository.save(accountData);
        return true;
    }

    @Override
    public List<AccountData> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountData getById(int accountId) {
        return getAccountById(accountId);
    }

    private AccountData getAccountById(int id) {
        AccountData accountData = accountRepository.findById(id).orElse(null);
        if (accountData == null) {
            throw new EntityNotFoundException(AccountData.class, "id", Integer.toString(id));
        }
        return accountData;
    }

    private AccountData getAccountByEmail(String email) {
        AccountData accountData = accountRepository.findAccountByEmail(email);
        if (accountData == null) {
            throw new EntityNotFoundException(AccountData.class, "email", email);
        }
        return accountData;
    }
}
