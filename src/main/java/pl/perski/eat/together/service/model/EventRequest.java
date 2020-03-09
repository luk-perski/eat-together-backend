package pl.perski.eat.together.service.model;

import lombok.AllArgsConstructor;
import pl.perski.eat.together.database.model.Event;

@AllArgsConstructor
public class EventRequest {
    private Event event;
    private int accountId;
}
