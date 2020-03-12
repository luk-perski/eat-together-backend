package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.Event;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;
import pl.perski.eat.together.utils.StringUtils;

import java.util.List;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;

    public EventService(EventRepository eventRepository, AccountRepository accountRepository) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event adEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        Account account = getAccountById(savedEvent.getCreatorAccountId());
        account.setEventHistory(StringUtils.addIdToList(account.getEventHistory(), event.getId())); //ogarnij lambdy
        accountRepository.save(account);
        return savedEvent;
    }

    @Override
    public List<Event> getAllForToday() {
        return null; //todo
    }

    @Override
    public Event getOne(int eventId) {
        return getEventById(eventId);
    }

    private Event getEventById(int id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EntityNotFoundException(Event.class, "id", Integer.toString(id));
        }
        return event;
    }

    private Account getAccountById(int id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new EntityNotFoundException(Account.class, "id", Integer.toString(id));
        }
        return account;
    }
}
