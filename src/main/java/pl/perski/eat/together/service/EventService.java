package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Event;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventRepository;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;

    public EventService(EventRepository eventRepository, AccountRepository accountRepository) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Event adEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        accountRepository.save(event.getAccount());
        return savedEvent;
    }
}
