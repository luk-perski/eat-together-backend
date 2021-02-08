package pl.perski.eat.together.api.v2.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.perski.eat.together.api.v2.dto.user.UserDtoGet;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.UserData;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(target = "user", source = "userData")
    AccountDtoGet toAccountDtoGet(AccountData account);

    AccountData toAccountData(AccountDtoPost accountDtoPost);

    @Mapping(target = "user", source = "userData")
    List<AccountDtoGet> toAccountDTOs(List<AccountData> accounts);
}
