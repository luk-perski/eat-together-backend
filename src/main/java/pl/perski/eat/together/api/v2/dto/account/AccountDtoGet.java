package pl.perski.eat.together.api.v2.dto.account;

import lombok.Data;
import pl.perski.eat.together.api.v2.dto.user.UserDtoGet;

@Data
public class AccountDtoGet {
    private Integer id;
    private String email;
    private UserDtoGet user;
}
