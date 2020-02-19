package pl.perski.eat.together.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.perski.eat.together.database.model.Group;
import pl.perski.eat.together.database.repository.GroupRepository;

import javax.validation.Valid;
import java.util.List;

public class GroupController {
    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/events")
    public List<Group> getAllEvents() {
        return groupRepository.findAll();
    }

    @PostMapping("/events")
    public Group addEvent(@Valid @RequestBody Group group) {
        return groupRepository.save(group);
    }
}
