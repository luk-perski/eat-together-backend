package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.perski.eat.together.database.model.Event;
import pl.perski.eat.together.database.repository.EventRepository;
import pl.perski.eat.together.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EventController {

    private final EventRepository eventRepository;
    private final EventService eventService;

    public EventController(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    //spoko by było tu dodać klasę service
    @GetMapping(value = "/events", consumes = "application/json")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping(value = "/events", consumes = "application/json", produces = "application/json")
    public Event addEvent(@Valid @RequestBody Event request) {
        return eventService.adEvent(request);
    }
}
