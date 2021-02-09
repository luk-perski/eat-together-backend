package pl.perski.eat.together.api.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.api.v2.dto.event.EventDtoGet;
import pl.perski.eat.together.api.v2.dto.event.EventDtoPost;
import pl.perski.eat.together.api.v2.dto.event.EventMapper;
import pl.perski.eat.together.database.model.EventData;
import pl.perski.eat.together.service.IEventService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Tag(name = "events", description = "the Events API")
@RestController
@RequestMapping("/events")
public class EventController {

    private final IEventService eventService;
    private final EventMapper eventMapper;

    @Operation(summary = "Get all Events", description = "Events", tags = {"events"})
    @GetMapping(consumes = "application/json")
    public List<EventDtoGet> getAllEvents(@Parameter(hidden = true) Authentication authentication) {
        return eventMapper.toEventsDTOs(eventService.getAll(authentication.getName()));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventDtoGet addEvent(@Valid @RequestBody EventDtoPost request, @Parameter(hidden = true) Authentication authentication) {
        EventData eventData = eventMapper.toEventData(request);
        return eventMapper.toEventDtoGet(eventService.addEvent(eventData, authentication.getName()));
    }

    @GetMapping(value = "/current", consumes = "application/json", produces = "application/json")
    public List<EventDtoGet> getAllFromNow(@Parameter(hidden = true) Authentication authentication) {
        return eventMapper.toEventsDTOs(eventService.getAllActiveFromNow(authentication.getName()));
    }

    @PutMapping(value = "/join/{eventId}", consumes = "application/json", produces = "text/plain")
    public String joinToEvent(@PathVariable int eventId, @Parameter(hidden = true) Authentication authentication) {
        return (eventService.joinToEvent(eventId, authentication.getName()));
    }

    @DeleteMapping(value = "/left/{eventId}", consumes = "application/json")
    public String leftFromEvent(@PathVariable int eventId, @Parameter(hidden = true) Authentication authentication) {
        return eventService.leftFromEvent(eventId, authentication.getName());
    }

    @DeleteMapping(value = "/deactivate", consumes = "application/json")
    public String deactivateEvent(@RequestParam int eventId, @Parameter(hidden = true) Authentication authentication) {
        return eventService.deactivateEvent(eventId, authentication.getName());
    }
}
