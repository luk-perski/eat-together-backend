package pl.perski.eat.together.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.EventData;
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
    public List<EventData> getAllEvents(Authentication authentication) {
        return eventService.getAll(authentication.getName());
    }

    @PostMapping(value = "/events", consumes = "application/json", produces = "application/json")
    public EventData addEvent(@Valid @RequestBody EventData request, Authentication authentication) {
        return eventService.addEvent(request, authentication.getName());
    }

    @GetMapping(value = "/events/current", consumes = "application/json", produces = "application/json")
    public List<EventData> getAllFromNow(Authentication authentication) {
        return eventService.getAllActiveFromNow(authentication.getName());
    }

    @PutMapping(value = "/events/join", consumes = "application/json", produces = "text/plain")
    public String joinToEvent(@RequestParam int eventId, Authentication authentication) {
        return eventService.joinToEvent(eventId, authentication.getName());
    }

    @DeleteMapping(value = "/events/left", consumes = "application/json")
    public String leftFromEvent(@RequestParam int eventId, Authentication authentication) {
        return eventService.leftFromEvent(eventId, authentication.getName());
    }

    @DeleteMapping(value = "/events/deactivate", consumes = "application/json")
    public String deactivateEvent(@RequestParam int eventId, Authentication authentication) {
        return eventService.deactivateEvent(eventId, authentication.getName());
    }
}
