package pl.perski.eat.together.api.v2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.api.v2.dto.account.AccountDtoGet;
import pl.perski.eat.together.api.v2.dto.account.AccountMapper;
import pl.perski.eat.together.api.v2.dto.user.UserMapper;
import pl.perski.eat.together.api.v2.model.AddAccountData;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.service.IAccountService;

import javax.validation.Valid;
import java.util.List;

import static pl.perski.eat.together.utils.SecurityConstants.SIGN_UP_URL;

//todo add Result as return
@RequiredArgsConstructor
@Tag(name = "accounts", description = "the Accounts API")
@RestController
public class AccountController {

    private final IAccountService accountService;
    private final AccountMapper accountMapper;
    private final UserMapper userMapper;

    @GetMapping("/accounts")
    public List<AccountData> getAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/accounts/{accountId}")
    public AccountDtoGet getAccountById(@PathVariable int accountId) {
        return accountMapper.toAccountDtoGet(accountService.getById(accountId));
    }

    @PostMapping(SIGN_UP_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addAccount(@Valid @RequestBody AddAccountData request) {
        AccountData accountData = accountMapper.toAccountData(request.getAccountData());
        UserData userData = userMapper.toUserData(request.getUserData());
        return accountService.addAccount(accountData, userData);
    }
}
