package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.GroupData;

import java.util.List;

public interface IGroupService {
    List<GroupData> getAll();

    GroupData getById(int groupId);

    GroupData add(GroupData groupData);

    String addUserToGroup(int userId, int groupId);
}
