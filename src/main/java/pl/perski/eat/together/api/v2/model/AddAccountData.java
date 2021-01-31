package pl.perski.eat.together.api.v2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.perski.eat.together.api.v2.dto.account.AccountDtoPost;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountData {
    @NotNull
    AccountDtoPost accountData;
    @NotNull
    UserDtoPost userData;
}
