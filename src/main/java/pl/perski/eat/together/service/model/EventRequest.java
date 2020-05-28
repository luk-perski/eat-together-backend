package pl.perski.eat.together.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.perski.eat.together.database.model.EventData;

@Data
@AllArgsConstructor
public class EventRequest {
    private EventData eventData;
    private int accountId;
}
