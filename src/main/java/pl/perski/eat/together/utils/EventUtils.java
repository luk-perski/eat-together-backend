package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.EventData;

public class EventUtils {
    public static void addUser(EventData eventData, int userId) {
        eventData.setParticipants(StringUtils.addIdToList(eventData.getParticipants(), userId));
    }

    public static void removeUser(EventData eventData, int userId) {
        eventData.setParticipants(StringUtils.removeIdFromList(eventData.getParticipants(), userId));
    }

}
