package pl.perski.eat.together.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.AccessDeniedException;
import pl.perski.eat.together.exeption.EntityNotFoundException;
import pl.perski.eat.together.utils.AccountUtils;
import pl.perski.eat.together.utils.Enums;
import pl.perski.eat.together.utils.StringUtils;

import java.util.List;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EventData> getAll(String email) {
        List<EventData> eventDataList = eventRepository.findAll();
        setJoin(eventDataList, email);
        translateParticipants(eventDataList);
        return eventDataList;
    }

    @Override
    public List<EventData> getAllActiveFromNow(String email) {
        List<EventData> eventDataList = eventRepository.findAllWithEventDateAfterNow();
        if (eventDataList != null) {
            setJoin(eventDataList, email);
            translateParticipants(eventDataList);
        }
        return eventDataList;
    }

    @Override
    public EventData addEvent(EventData eventData, String email) {
        AccountData accountData = getAccountByEmail(email);
        eventData.setStatus(Enums.EVENT_STATUS.ACTIVE.ordinal());
        eventData.setCreatorAccountId(accountData.getId());
        eventData.setCreatorName(String.format("%s (%s)",
                accountData.getUserData().getFirstName(), accountData.getUserData().getCompanyName()));
        EventData savedEventData = eventRepository.save(eventData);
        addEventToAccountHistory(accountData, eventData.getId());
        addUserToEventParticipants(eventData, accountData);
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
        addEventToAccountHistory(accountData, eventId);
        addUserToEventParticipants(eventData, accountData);
        return String.format("User %s has been added to event(%s)", accountData.getUserData().getFirstName(), eventData.getPlaceName());
    }

    @Override
    public String leftFromEvent(int eventId, String email) {
        EventData eventData = getEventById(eventId);
        AccountData accountData = getAccountByEmail(email);
        removeUserFromEventParticipants(eventData, accountData);
        removeEventFromAccountEventHistory(accountData, eventId);
        return String.format("User %s has been removed from event(%s)", accountData.getUserData().getFirstName(), eventData.getPlaceName());
    }

    @Override
    public String deactivateEvent(int eventId, String accountEmail) {
        EventData eventData = getEventById(eventId);
        AccountData accountData = getAccountByEmail(accountEmail);
        if (eventData.getCreatorAccountId() != accountData.getId()) {
            throw new AccessDeniedException("Operation denied! Account with this id is not creator of the event.");
        }
        eventData.setStatus(Enums.EVENT_STATUS.DISABLED.ordinal());
        removeEventFromAccountEventHistory(accountData, eventId);
        removeUserFromEventParticipants(eventData, accountData);
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

    private void addEventToAccountHistory(AccountData accountData, int eventId) {
        AccountUtils.addEventToHistory(accountData, eventId);
        accountRepository.save(accountData);
    }

    private void addUserToEventParticipants(EventData eventData, AccountData accountData) {
//        eventData.setParticipants(StringUtils.addIdToList(eventData.getParticipants(), accountData.getUserData().getId()));
        eventData.addUser(accountData.getUserData().getId());
        eventRepository.save(eventData);
    }

    private void removeEventFromAccountEventHistory(AccountData accountData, int eventId) {
//        accountData.setEventHistory(StringUtils.removeIdFromList(accountData.getEventHistory(), eventId));
        AccountUtils.removeEventFromHistory(accountData, eventId);
        accountRepository.save(accountData);
    }

    private void removeUserFromEventParticipants(EventData eventData, AccountData accountData) {
//        eventData.setParticipants(StringUtils.removeIdFromList(eventData.getParticipants(), accountData.getUserData().getId()));
        eventData.removeUser(accountData.getUserData().getId());
        eventRepository.save(eventData);
    }

    private void setJoin(List<EventData> eventDataList, String email) {
        AccountData accountData = getAccountByEmail(email);
        for (EventData x : eventDataList) {
            if (x.getParticipants() != null) {
                x.setCallerJoin(StringUtils.checkIfListContainsId(x.getParticipants(), accountData.getUserData().getId()));
                x.setCallerIsCreator(x.getCreatorAccountId() == accountData.getId());
            }
        }
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

    private void translateParticipants(List<EventData> eventDataList) {
        for (EventData x : eventDataList) {
            if (x.getParticipants() != null) {
                String names = "";
                for (String participant : x.getParticipants().split(";")) {
                    if (!participant.isEmpty()) {
                        UserData userData = userRepository.getOne(Integer.parseInt(participant));
                        if (names.length() > 1) {
                            names = names + "\n";
                        }
                        names = String.format("%s%s %s (%s)", names, userData.getFirstName(), userData.getLastName(), userData.getCompanyName());
                    }
                }
                x.setParticipants(names);
            }
        }
    }

}
//String.format("%s%s %s (%s)", names[0], userData.getFirstName(), userData.getLastName(), userData.getCompanyName());
