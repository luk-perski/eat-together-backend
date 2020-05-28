package pl.perski.eat.together.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountData {
    @NotNull
    AccountData accountData;
    @NotNull
    UserData userData;
}
