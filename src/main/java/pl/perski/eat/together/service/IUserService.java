package pl.perski.eat.together.service;

import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;
import pl.perski.eat.together.api.v2.dto.user.UserMapper;
import pl.perski.eat.together.database.model.UserData;

import java.util.List;

public interface IUserService {
    List<UserData> getAll();

    UserData update(UserDtoPost userDto, String accountEmail, UserMapper userMapper);

    UserData getById(int userId);

    List<UserData> getManyByIds(List<Integer> idList);

    UserData getByAccountEmail(String name);
}
