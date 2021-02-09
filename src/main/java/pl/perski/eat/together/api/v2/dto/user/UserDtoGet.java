package pl.perski.eat.together.api.v2.dto.user;

import lombok.Data;


@Data
public class UserDtoGet {
    private Integer id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String description;
}
