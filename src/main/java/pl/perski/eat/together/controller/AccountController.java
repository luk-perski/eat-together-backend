package pl.perski.eat.together.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.service.AccountService;
import pl.perski.eat.together.service.IAccountService;
import pl.perski.eat.together.service.model.AddAccountData;

import java.util.List;

import static pl.perski.eat.together.utils.SecurityConstants.SIGN_UP_URL;


@RestController
public class AccountController {

    private final IAccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountData> getAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/accounts/{accountId}")
    public AccountData getAccountById(@PathVariable int accountId) {
        return accountService.getById(accountId);
    }

    @PostMapping(SIGN_UP_URL)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AddAccountData addAccount(@RequestBody AddAccountData addAccountData) {
        return accountService.addAccount(addAccountData);
    }

    @PutMapping("/accounts/{accountId}/{eventId}")
    public AccountData addEventToAccount(@PathVariable int accountId, @PathVariable int eventId) {
        return accountService.addEventToAccount(accountId, eventId);
    }
}
