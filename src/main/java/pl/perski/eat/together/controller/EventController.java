package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.perski.eat.together.database.model.Event;
import pl.perski.eat.together.service.EventService;
import pl.perski.eat.together.service.IEventService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EventController {

    private final IEventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/events", consumes = "application/json")
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @PostMapping(value = "/events", consumes = "application/json", produces = "application/json")
    public Event addEvent(@Valid @RequestBody Event request) {
        return eventService.adEvent(request);
    }
}
