package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.User;

import java.util.List;

public interface IUserService {
    User add(User user);

    User update(User user);

    User getById(int userId);

    List<User> getManyByIds(List<Integer> idList);
}
