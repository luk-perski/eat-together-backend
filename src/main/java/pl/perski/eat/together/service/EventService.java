package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.Event;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.utils.StringUtils;

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
        Account account = accountRepository.findById(event.getCreatorAccountId()).get(); //todo obsługa błędu
        account.setEventHistory(StringUtils.addIdToList(account.getEventHistory(), event.getId())); //ogarnij lambdy
        accountRepository.save(account);
        return savedEvent;
    }
}
