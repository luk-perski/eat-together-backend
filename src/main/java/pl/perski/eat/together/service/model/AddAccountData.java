package pl.perski.eat.together.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountData {
    @NotNull
    AccountData accountData;
    @NotNull
    UserData userData;
}
