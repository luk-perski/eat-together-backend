package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.AccountData;

public class AccountUtils {

    public static void addGroup(AccountData accountData, int groupId) {
        accountData.setUserGroups(StringUtils.addIdToList(accountData.getUserGroups(), groupId));
    }
}
