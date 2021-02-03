package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.GroupData;
import pl.perski.eat.together.database.repository.GroupRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

public class GroupUtils {

    public static GroupData getGroupById(GroupRepository groupRepository, int id) {
        GroupData groupData = groupRepository.findById(id).orElse(null);
        if (groupData == null) {
            throw new EntityNotFoundException(GroupData.class, "id", Integer.toString(id));
        }
        return groupData;
    }
}
