package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.service.AccountService;
import pl.perski.eat.together.service.IAccountService;

import java.util.List;

import static pl.perski.eat.together.utils.SecurityConstants.SIGN_UP_URL;


@RestController
public class AccountController {

    private final IAccountService accountService;

    public AccountController(AccountService accountService) {
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

    @PostMapping(SIGN_UP_URL)
    public Account addAccount( @RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PutMapping("/accounts/{accountId}/{eventId}")
    public Account addEventToAccount(@PathVariable int accountId, @PathVariable int eventId) {
        return accountService.addEventToAccount(accountId, eventId);
    }
}
