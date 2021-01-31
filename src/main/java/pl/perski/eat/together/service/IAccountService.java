package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;

import java.util.List;

public interface IAccountService {
    Boolean addAccount(AccountData accountData, UserData userData);

    List<AccountData> getAll();

    AccountData getById(int accountId);
}
