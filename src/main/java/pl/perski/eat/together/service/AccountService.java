package pl.perski.eat.together.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;
import pl.perski.eat.together.service.model.AddAccountData;
import pl.perski.eat.together.utils.StringUtils;

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
    public AddAccountData addAccount(AddAccountData addAccountData) {
        AccountData accountData = addAccountData.getAccountData();
        accountData.setUserData(userRepository.save(addAccountData.getUserData()));
        accountData.setEmail(StringUtils.removeDotsFromEmail(accountData.getEmail()));
        accountData.setPassword(bCryptPasswordEncoder.encode(accountData.getPassword()));
        accountRepository.save(accountData);
        addAccountData.getAccountData().setPassword("");
        return addAccountData;
    }

    @Override
    public List<AccountData> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountData getById(int accountId) {
        return getAccountById(accountId);
    }

    @Override
    public AccountData addEventToAccount(int accountId, int eventId) {
        AccountData accountData = getAccountById(accountId);
        accountData.addEventToHistory(eventId);
        return accountRepository.save(accountData);
    }

    private AccountData getAccountById(int id) {
        AccountData accountData = accountRepository.findById(id).orElse(null);
        if (accountData == null) {
            throw new EntityNotFoundException(AccountData.class, "id", Integer.toString(id));
        }
        return accountData;
    }
}
