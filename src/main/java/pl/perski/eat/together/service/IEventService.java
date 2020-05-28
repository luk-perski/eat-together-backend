package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.EventData;

import java.util.List;

public interface IEventService {
    List<EventData> getAll(String email);

    EventData addEvent(EventData eventData, String email);

    List<EventData> getAllForToday(String email);

    EventData getOne(int eventId);

    List<EventData> getAllActiveFromNow(String email);

    String joinToEvent(int eventId, String email);

    String leftFromEvent(int eventId, String email);

    String deactivateEvent(int eventId, String accountEmail);
}
