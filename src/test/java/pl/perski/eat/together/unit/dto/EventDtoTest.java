package pl.perski.eat.together.unit.dto;

import org.junit.Test;
import pl.perski.eat.together.api.v2.dto.event.EventDtoGet;
import pl.perski.eat.together.api.v2.dto.event.EventDtoPost;
import pl.perski.eat.together.api.v2.dto.event.EventMapper;
import pl.perski.eat.together.api.v2.dto.event.EventMapperImpl;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.enums.EventStatus;

import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

public class EventDtoTest {

    private EventMapper eventMapper() {
        return new EventMapperImpl();
    }

    @Test
    public void whenConvertEventDataToEventDtoGet_thenCorrect() {
        EventData eventData = EventData.builder()
                .id(2)
                .creatorName(randomAlphabetic(8))
                .date(new Date())
                .placeName(randomAlphabetic(8))
                .placeLocation(randomAlphabetic(8))
                .description(randomAlphabetic(256))
                .isPublic(true)
                .status(EventStatus.ACTIVE)
                .callerJoin(false)
                .callerIsCreator(true)
                .locationLatitude(52.409528)
                .locationLongitude(16.912463)
                .build();

        EventDtoGet eventDtoGet = eventMapper().toEventDtoGet(eventData);
        assertEquals(eventData.getId(), eventDtoGet.getId());
        assertEquals(eventData.getCreatorName(), eventDtoGet.getCreatorName());
        assertEquals(eventData.getDate(), eventDtoGet.getDate());
        assertEquals(eventData.getPlaceName(), eventDtoGet.getPlaceName());
        assertEquals(eventData.getPlaceLocation(), eventDtoGet.getPlaceLocation());
        assertEquals(eventData.getDescription(), eventDtoGet.getDescription());
        assertEquals(eventData.getIsPublic(), eventDtoGet.getIsPublic());
        assertEquals(eventData.getStatus().toString(), eventDtoGet.getStatus());
        assertEquals(eventData.isCallerJoin(), eventDtoGet.isCallerJoin());
        assertEquals(eventData.isCallerIsCreator(), eventDtoGet.isCallerIsCreator());
        assertEquals(eventData.getLocationLatitude(), eventDtoGet.getLocationLatitude(), 0);
        assertEquals(eventData.getLocationLongitude(), eventDtoGet.getLocationLongitude(), 0);
    }

    @Test
    public void whenConvertEventDtoPostToEventData_thenCorrect() {
        EventDtoPost eventDtoPost = EventDtoPost.builder()
                .date(new Date())
                .description(randomAlphabetic(8))
                .placeName(randomAlphabetic(8))
                .placeLocation(randomAlphabetic(8))
                .description(randomAlphabetic(8))
                .isPublic(true)
                .status(EventStatus.ACTIVE.toString())
                .locationLatitude(52.409528)
                .locationLongitude(16.912463)
                .build();

        EventData eventData = eventMapper().toEventData(eventDtoPost);
        assertEquals(eventDtoPost.getDate(), eventData.getDate());
        assertEquals(eventDtoPost.getDescription(), eventData.getDescription());
        assertEquals(eventDtoPost.getPlaceName(), eventData.getPlaceName());
        assertEquals(eventDtoPost.getPlaceLocation(), eventData.getPlaceLocation());
        assertEquals(eventDtoPost.getIsPublic(), eventData.getIsPublic());
        assertEquals(eventDtoPost.getStatus(), eventData.getStatus().toString());
        assertEquals(eventDtoPost.getLocationLatitude(), eventData.getLocationLatitude(), 0);
        assertEquals(eventDtoPost.getLocationLongitude(), eventData.getLocationLongitude(), 0);
    }
}
