package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.Account;

import java.util.List;

public interface IAccountService {
    Account addAccount(Account account);

    List<Account> getAll();

    Account getById(int accountId);
}
