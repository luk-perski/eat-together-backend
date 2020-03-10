package pl.perski.eat.together.service;

import pl.perski.eat.together.database.model.Event;

import java.util.List;

public interface IEventService {
    List<Event> getAll();

    Event adEvent(Event event);

    List<Event> getAllForToday();

    Event getOne(int eventId);
}
