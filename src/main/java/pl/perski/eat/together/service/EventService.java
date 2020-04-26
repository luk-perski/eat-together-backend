package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.EventData;
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
    public List<EventData> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public EventData adEvent(EventData eventData) {
        EventData savedEventData = eventRepository.save(eventData);
        AccountData accountData = getAccountById(savedEventData.getCreatorAccountId());
        accountData.setEventHistory(StringUtils.addIdToList(accountData.getEventHistory(), eventData.getId())); //ogarnij lambdy
        accountRepository.save(accountData);
        return savedEventData;
    }

    @Override
    public List<EventData> getAllForToday() {
        return null; //todo
    }

    @Override
    public EventData getOne(int eventId) {
        return getEventById(eventId);
    }

    private EventData getEventById(int id) {
        EventData eventData = eventRepository.findById(id).orElse(null);
        if (eventData == null) {
            throw new EntityNotFoundException(EventData.class, "id", Integer.toString(id));
        }
        return eventData;
    }

    private AccountData getAccountById(int id) {
        AccountData accountData = accountRepository.findById(id).orElse(null);
        if (accountData == null) {
            throw new EntityNotFoundException(AccountData.class, "id", Integer.toString(id));
        }
        return accountData;
    }
}
