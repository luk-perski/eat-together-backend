package pl.perski.eat.together;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.service.EventService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

    @MockBean
    EventRepository eventRepository;

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    EventService eventService;

    @Test
    public void testCreateEventCreatorName(){
        String email = "aaa@aaa.com";
        String firstName = randomAlphabetic(8);
        String companyName = randomAlphabetic(8);
        String creatorName = String.format("%s (%s)",
                firstName, companyName);
        UserData userData = UserData.builder()
                .firstName(firstName)
                .companyName(companyName)
                .build();
        AccountData accountData = AccountData.builder()
                .id(1)
                .userData(userData)
                .build();
        EventData eventData = new EventData();

        when(accountRepository.findAccountByEmail(email)).thenReturn(accountData);
        when(eventRepository.save(eventData)).thenReturn(eventData);
        assertEquals(creatorName, eventService.addEvent(eventData, email).getCreatorName());
    }
}
