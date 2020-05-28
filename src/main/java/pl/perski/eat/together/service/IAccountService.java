package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.service.model.AddAccountData;

import java.util.List;

public interface IAccountService {
    AddAccountData addAccount(AddAccountData accountData);

    List<AccountData> getAll();

    AccountData getById(int accountId);
}
