package pl.perski.eat.together.api.v2.dto.account;

import lombok.Builder;
import lombok.Data;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;

@Data
@Builder
public class AccountDtoPost {
    private String password;
    private String email;
    private UserDtoPost user;
}