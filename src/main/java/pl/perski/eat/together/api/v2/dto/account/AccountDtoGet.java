package pl.perski.eat.together.api.v2.dto.account;

import lombok.Builder;
import lombok.Data;
import pl.perski.eat.together.api.v2.dto.user.UserDtoGet;

@Data
@Builder
public class AccountDtoGet {
    private int id;
    private String email;
    private UserDtoGet user;
}
