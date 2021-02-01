package pl.perski.eat.together.api.v2.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.perski.eat.together.database.model.AccountData;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountDtoGet toAccountDtoGet(AccountData account);

    AccountData toAccountData(AccountDtoPost accountDtoPost);

    List<AccountDtoGet> toAccountDTOs(List<AccountData> accounts);
}
