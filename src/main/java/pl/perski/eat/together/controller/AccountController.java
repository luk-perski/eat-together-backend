package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.service.AccountService;
import pl.perski.eat.together.service.IAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    private final IAccountService accountService;

    public AccountController(AccountRepository accountRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccountById(@PathVariable int accountId) {
        return accountService.getById(accountId);
    }

    @PostMapping("/accounts")
    public Account addAccount(@Valid @RequestBody Account account) {
        return accountService.addAccount(account);
    }


    @PutMapping("/accounts/{accountId}/{eventId}")
    public Account addEventToAccount(@PathVariable int accountId, @PathVariable int eventId) {
        return accountService.addEventToAccount(accountId, eventId);
    }
}
