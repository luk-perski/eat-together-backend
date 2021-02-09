package pl.perski.eat.together.dto;


import org.junit.Test;
import pl.perski.eat.together.api.v2.dto.account.AccountDtoGet;
import pl.perski.eat.together.api.v2.dto.account.AccountDtoPost;
import pl.perski.eat.together.api.v2.dto.account.AccountMapper;
import pl.perski.eat.together.api.v2.dto.account.AccountMapperImpl;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

public class AccountDtoTest {

    private AccountMapper accountMapper() {
        return new AccountMapperImpl();
    }

    @Test
    public void whenConvertAccountDataToAccountDtoGet_thenCorrect() {

        UserData userData = UserData.builder().id(2).build();

        AccountData accountData = AccountData.builder()
                .id(1)
                .userData(userData)
                .email("abc@dfg.com")
                .build();

        AccountDtoGet accountDtoGet = accountMapper().toAccountDtoGet(accountData);
        assertEquals(accountData.getId(), accountDtoGet.getId());
        assertEquals(accountData.getUserData().getId(), accountDtoGet.getUser().getId());
        assertEquals(accountData.getEmail(), accountDtoGet.getEmail());
    }

    @Test
    public void whenConvertAccountDtoPostToAccountData_thenCorrect() {

        UserDtoPost userDtoPost = UserDtoPost.builder().firstName(randomAlphabetic(8)).build();

        AccountDtoPost accountDtoPost = AccountDtoPost.builder()
                .email("abc@def.com")
                .password(randomAlphabetic(8))
                .user(userDtoPost)
                .build();

        AccountData accountData = accountMapper().toAccountData(accountDtoPost);
        assertEquals(accountDtoPost.getEmail(), accountData.getEmail());
        assertEquals(accountDtoPost.getPassword(), accountData.getPassword());
        assertEquals(accountDtoPost.getUser().getFirstName(), accountData.getUserData().getFirstName());
    }
}
