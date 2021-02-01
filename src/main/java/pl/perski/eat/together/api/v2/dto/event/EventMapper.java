package pl.perski.eat.together.api.v2.dto.event;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.perski.eat.together.database.model.EventData;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    EventDtoGet toEventDtoGet(EventData event);

    EventData toEventData(EventDtoPost eventDtoPost);

    List<EventDtoGet> toEventsDTOs(List<EventData> events);
}
