package pl.perski.eat.together.api.v2.dto.account;

import org.mapstruct.Mapper;
import pl.perski.eat.together.database.model.AccountData;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountDtoGet toAccountDtoGet(AccountData account);

    AccountDtoPost toAccountDtoPost(AccountData accountData);

    AccountData toAccountData(AccountDtoPost accountDtoPost);

    List<AccountDtoGet> toAccountDTOs(List<AccountData> accounts);
}
