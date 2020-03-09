package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    public AccountController(AccountRepository accountRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

//    @GetMapping("/accounts/events/{accountId}")
//    public String[]  getUserEvents(@PathVariable String accountId) {
//        return accountRepository.getAccountEvents(accountId);
//    }

    @PostMapping("/accounts")
    public Account addAccount(@Valid @RequestBody Account account) {
        return accountService.addAccount(account);
    }


//    @PutMapping("/accounts/{accountId}/{eventId}")
//    public Account addEventToAccount (@PathVariable Long accountId, @PathVariable Long eventId){
//        return accountRepository.findById(accountId).map(account -> {
//            return accountRepository.
//        }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + accountId));
//    }
}
