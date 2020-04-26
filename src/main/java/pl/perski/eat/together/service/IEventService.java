package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.EventData;

import java.util.List;

public interface IEventService {
    List<EventData> getAll();

    EventData adEvent(EventData eventData);

    List<EventData> getAllForToday();

    EventData getOne(int eventId);
}
