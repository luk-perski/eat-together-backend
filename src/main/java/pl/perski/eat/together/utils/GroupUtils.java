package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.GroupData;

public class GroupUtils {
    public static void addUser(GroupData groupData, int userId) {
        groupData.setUsersId(StringUtils.addIdToList(groupData.getUsersId(), userId));
    }
}
