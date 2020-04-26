package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.UserData;

import java.util.List;

public interface IUserService {
    List<UserData> getAll();

//    UserData add(UserData userData);

    UserData update(UserData userData);

    UserData getById(int userId);

    List<UserData> getManyByIds(List<Integer> idList);
}
