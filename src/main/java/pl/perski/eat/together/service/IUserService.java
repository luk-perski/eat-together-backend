package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.UserData;

import java.util.List;

public interface IUserService {
    List<UserData> getAll();

    UserData update(UserData userData, String accountEmail);

    UserData getById(int userId);

    List<UserData> getManyByIds(List<Integer> idList);

    UserData getByAccountEmail(String name);
}
