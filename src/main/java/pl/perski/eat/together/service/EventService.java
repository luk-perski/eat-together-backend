package pl.perski.eat.together.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.database.model.EventParticipationData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventParticipationRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.enums.EventStatus;
import pl.perski.eat.together.exeption.AccessDeniedException;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final EventParticipationRepository eventParticipationRepository;

    //todo add EventParticipation info
    @Override
    public List<EventData> getAll(String email) {
        return eventRepository.findAll();
    }

    @Override
    public List<EventData> getAllActiveFromNow(String email) {
        return eventRepository.findAllWithEventDateAfterNow();
    }

    @Override
    public EventData addEvent(EventData eventData, String email) {
        AccountData accountData = getAccountByEmail(email);
        eventData.setStatus(EventStatus.ACTIVE);
        eventData.setCreatorAccountId(accountData.getId());
        eventData.setCreatorName(String.format("%s (%s)",
                accountData.getUserData().getFirstName(), accountData.getUserData().getCompanyName()));
        EventData savedEventData = eventRepository.save(eventData);
        addEventParticipant(eventData, accountData.getUserData());
        return savedEventData;
    }

    @Override
    public List<EventData> getAllForToday(String email) {
        throw new NotImplementedException("Not implemented \"Get All For Today\""); //todo
    }

    @Override
    public EventData getOne(int eventId) {
        return getEventById(eventId);
    }

    @Override
    public String joinToEvent(int eventId, String email) {
        EventData eventData = getEventById(eventId);
        AccountData accountData = getAccountByEmail(email);
        addEventParticipant(eventData, accountData.getUserData());
        return String.format("User %s has been added to event(%s)", accountData.getUserData().getFirstName(), eventData.getPlaceName());
    }

    @Override
    public String leftFromEvent(int eventId, String email) {
        EventData eventData = getEventById(eventId);
        AccountData accountData = getAccountByEmail(email);
        removeUserFromEventParticipants(eventId, accountData);
        return String.format("User %s has been removed from event(%s)", accountData.getUserData().getFirstName(), eventData.getPlaceName());
    }

    @Override
    public String deactivateEvent(int eventId, String accountEmail) {
        EventData eventData = getEventById(eventId);
        AccountData accountData = getAccountByEmail(accountEmail);
        if (eventData.getCreatorAccountId() != accountData.getId()) {
            throw new AccessDeniedException("Operation denied! Account with this id is not creator of the event.");
        }
        eventData.setStatus(EventStatus.DISABLED);
        removeUserFromEventParticipants(eventId, accountData);
        return String.format("User %s has been removed from event(%s) and event has been deactivate.", accountData.getUserData().getFirstName(), eventData.getPlaceName());
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

    private AccountData getAccountByEmail(String email) {
        AccountData accountData = accountRepository.findAccountByEmail(email);
        if (accountData == null) {
            throw new EntityNotFoundException(AccountData.class, "email", email);
        }
        return accountData;
    }

    private void addEventParticipant(EventData eventData, UserData userData) {
        if (eventParticipationRepository.findByEvent_IdAndUser_Id(eventData.getId(), userData.getId()).size() > 0) {
            throw new DataIntegrityViolationException("The user is already a participant in the event");
        }
        EventParticipationData eventParticipation = EventParticipationData.builder()
                .event(eventData)
                .user(userData)
                .build();
        eventParticipationRepository.save(eventParticipation);
    }

    private void removeUserFromEventParticipants(int eventId, AccountData accountData) {
        int userId = accountData.getUserData().getId();
        List<EventParticipationData> eventParticipationData = eventParticipationRepository.findByEvent_IdAndUser_Id(eventId, userId);
        eventParticipationRepository.deleteAll(eventParticipationData);
    }

    private void setJoinToAll(List<EventData> eventDataList, int userId) {
        for (EventData x : eventDataList) {
            x.setCallerJoin(true);
        }
    }

    private int getUserId(String email) {
        return getAccountByEmail(email).getUserData().getId();
    }

    private int getAccountId(String email) {
        return getAccountByEmail(email).getId();
    }
}
