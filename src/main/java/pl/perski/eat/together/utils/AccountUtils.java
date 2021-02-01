package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.AccountData;

public class AccountUtils {

    public static void addEventToHistory(AccountData accountData, int eventId) {
        accountData.setEventHistory(StringUtils.addIdToList(accountData.getEventHistory(), eventId));
    }

    public static void removeEventFromHistory(AccountData accountData, int eventId) {
        accountData.setEventHistory(StringUtils.removeIdFromList(accountData.getEventHistory(), eventId));
    }

    public static void addGroup(AccountData accountData, int groupId) {
        accountData.setUserGroups(StringUtils.addIdToList(accountData.getUserGroups(), groupId));
    }
}
