package pl.perski.eat.together.api.v2.dto.user;

import org.mapstruct.*;
import pl.perski.eat.together.database.model.UserData;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDtoGet toUserDtoGet(UserData user);

    UserDtoPost toUserDtoPost(UserData userData);

    UserData toUserData(UserDtoPost userDtoPost);

    List<UserDtoGet> toUsersDTOs(List<UserData> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateUserFromDto (UserDtoPost dto, @MappingTarget UserData user);
}