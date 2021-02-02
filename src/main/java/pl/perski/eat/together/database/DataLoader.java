package pl.perski.eat.together.database;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.database.model.EventParticipationData;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.EventParticipationRepository;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.enums.EventStatus;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventParticipationRepository eventParticipationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String databaseInitializationMode;


    @Override
    public void run(ApplicationArguments args) {

        if (databaseInitializationMode.equals("create-drop")) {
            try {
                List<AccountData> accountList = new ArrayList<>();
                List<EventData> eventList = new ArrayList<>();
                AccountData
                        .builder()
                        .email("lukasz.perski@google.com")
                        .password(bCryptPasswordEncoder.encode(Character.toString('q')))
                        .userData(userRepository.save(UserData
                                .builder()
                                .firstName("Łukasz")
                                .lastName("Perski")
                                .companyName("Google")
                                .userLocationLatitude(52.409528)
                                .userLocationLongitude(16.912463)
                                .userLocationAddress("Roosevelta 18, Jeżyce, Poznań")
                                .distanceRange(1.5)
                                .build()))
                        .build();

                for (int i = 97; i <= 113; i++) {
                    char c = (char) i;
                    char cUpperCase = (char) (i - 32);

                    accountList.add(AccountData
                            .builder()
                            .email(String.format("%c@%c.com", c, c))
                            .password(bCryptPasswordEncoder.encode(Character.toString(c)))
                            .userData(userRepository.save(UserData
                                    .builder()
                                    .firstName(String.format("Name %c", cUpperCase))
                                    .lastName(String.format("Surname %c", cUpperCase)                                                                       )
                                    .companyName(String.format("Company %c", cUpperCase))
                                    .userLocationLatitude(52.409528)
                                    .userLocationLongitude(16.912463)
                                    .userLocationAddress("Roosevelta 18, Jeżyce, Poznań")
                                    .distanceRange(1.5)
                                    .build()))
                            .build());
                }
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 2)))
                        .placeName("Frontiera")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Lukasz (Google)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 37, 61-815 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 4)))
                        .placeName("Ming Wok")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Malgorzata (Facebook)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404870)
                        .placeLocation("Ratajczaka 18, 61-891 Poznań")
                        .locationLatitude(16.924568)
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 2)))
                        .placeName("Zen On")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Piotr (Amazon)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404870)
                        .locationLatitude(16.924568)
                        .placeLocation("Ratajczaka 25, 61-814 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 2)))
                        .placeName("Chłopskie Jadło")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.408756)
                        .locationLatitude(16.920957)
                        .placeLocation("Fredry 12, 61-701 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                        .placeName("Winestone")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Sebastian (Samsung)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.408921)
                        .locationLatitude(16.912201)
                        .placeLocation("Roosevelta 20, 60-829 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                        .placeName("Manekin")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Joanna (Microsoft)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.410567)
                        .locationLatitude(16.920957)
                        .placeLocation("Mickiewicza 24, 60-835 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                        .placeName("Supra")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Adrian (IBM)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Thai Fast WOK")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Bar a Boo")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Malgorzata (Facebook)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 3)))
                        .placeName("Czerwone Sombrero")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Bar Mleczny")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Ami ci moi")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Violet Sushi")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 5)))
                        .placeName("Kraszkebab")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                        .placeName("PyraBar")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Tapasta")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Khao San")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("Marina")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                eventList.add(EventData
                        .builder()
                        .date(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 4)))
                        .placeName("4 alternatywy")
                        .creatorAccountId(1)
                        .description("Who wanna go with me?")
                        .creatorName("Jan (Apple)")
                        .callerIsCreator(true)
                        .callerIsCreator(true)
                        .locationLongitude(52.404950)
                        .locationLatitude(16.924498)
                        .placeLocation("Ratajczaka 33, 61-816 Poznań")
                        .status(EventStatus.ACTIVE)
                        .build());
                accountRepository.saveAll(accountList);
                Optional<AccountData> eventCreatorAccount = accountRepository.findById(1);
                if (!eventCreatorAccount.isPresent()) {
                    throw new EntityNotFoundException(AccountData.class, "Something went wrong during Data loader");
                }
                List<EventParticipationData> eventParticipationDataList = new ArrayList<>();
                UserData eventCreatorUser = eventCreatorAccount.get().getUserData();
                for (EventData eventData : eventList) {
                    EventParticipationData eventParticipationData = EventParticipationData.builder()
                            .event(eventData)
                            .user(eventCreatorUser)
                            .build();
                    eventParticipationDataList.add(eventParticipationData);
                }
                eventRepository.saveAll(eventList);
                eventParticipationRepository.saveAll(eventParticipationDataList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
