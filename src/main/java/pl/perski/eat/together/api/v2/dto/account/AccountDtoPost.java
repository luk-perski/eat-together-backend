package pl.perski.eat.together.api.v2.dto.account;

import lombok.Data;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;

@Data
public class AccountDtoPost {
    private String password;
    private String email;
    private String userGroups;
    private UserDtoPost user;
}