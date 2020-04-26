package pl.perski.eat.together.service.model;

import lombok.AllArgsConstructor;
import pl.perski.eat.together.database.model.EventData;

@AllArgsConstructor
public class EventRequest {
    private EventData eventData;
    private int accountId;
}
