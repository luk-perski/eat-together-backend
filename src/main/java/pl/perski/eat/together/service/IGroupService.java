package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.Group;

import java.util.List;

public interface IGroupService {
    List<Group> getAll();

    Group add(Group group);

    String addUserToGroup(int userId, int groupId);
}
